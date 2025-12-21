package se.ifmo.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import se.ifmo.gen.model.DragonCreate;
import se.ifmo.gen.model.ImportDragonResponse;
import se.ifmo.gen.model.ImportOperation;
import se.ifmo.gen.model.ImportOperationStatus;
import se.ifmo.models.ImportOperationEntity;
import se.ifmo.models.ImportStatus;
import se.ifmo.models.UserRole;
import se.ifmo.repositories.ImportOperationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImportService {
    private final DragonService dragonService;
    private final ImportOperationRepository importOperationRepository;
    private final ObjectMapper objectMapper;
    private final FileStorageService fileStorageService;

    public ImportDragonResponse importDragonsFromFile(MultipartFile file, UserRole userRole) {
        String tempFileKey = null;
        String permanentFileKey;
        
        try {
            // 1 фаза: prepare (временно кладем файл в минио)
            tempFileKey = fileStorageService.prepareFileStorage(file);
            
            List<DragonCreate> dragons = objectMapper.readValue(
                    file.getInputStream(),
                    new TypeReference<>() {
                    }
            );
            
            ImportDragonResponse response = importDragons(dragons, userRole, tempFileKey);

            // если мы здесь то транзакция бд была успешной
            // 2 фаза: commit (перемещаем файлы во постоянное хранилище)
            if (response.getStatus() == ImportOperationStatus.SUCCESS) {
                permanentFileKey = fileStorageService.commitFileStorage(tempFileKey);
                updateImportOperationFileKey(response.getOperationId(), permanentFileKey);
                response.setFileKey(permanentFileKey);
            } else {
                fileStorageService.rollbackFileStorage(tempFileKey);
            }
            
            return response;
        } catch (IOException e) {
            // роллбэк
            if (tempFileKey != null) {
                log.error("First rollback:{}", String.valueOf(e));
                fileStorageService.rollbackFileStorage(tempFileKey);
            }
            
            ImportOperationEntity saved = saveFailedOperation(userRole, "Failed to parse JSON file: " + e.getMessage(), null);
            return new ImportDragonResponse()
                    .operationId(saved.getId().intValue())
                    .status(ImportOperationStatus.FAILED)
                    .addedCount(0)
                    .errorMessage("Failed to parse JSON file: " + e.getMessage());
        } catch (RuntimeException e) {
            // роллбэк
            if (tempFileKey != null) {
                log.error("Second rollback:{}", String.valueOf(e));
                fileStorageService.rollbackFileStorage(tempFileKey);
            }
            
            throw e;
        }
    }

    public ImportDragonResponse importDragons(List<DragonCreate> dragons, UserRole userRole, String tempFileKey) {
        try {
            int count = performImport(dragons);
            ImportOperationEntity saved = saveSuccessOperation(userRole, count, tempFileKey);

            return new ImportDragonResponse()
                    .operationId(saved.getId().intValue())
                    .status(ImportOperationStatus.SUCCESS)
                    .addedCount(count)
                    .fileKey(tempFileKey);

        } catch (CannotAcquireLockException e) {
            saveFailedOperation(userRole, "Concurrent modification conflict", tempFileKey);
            throw e;
        } catch (Exception e) {
            ImportOperationEntity saved = saveFailedOperation(userRole, e.getMessage(), tempFileKey);

            return new ImportDragonResponse()
                    .operationId(saved.getId().intValue())
                    .status(ImportOperationStatus.FAILED)
                    .addedCount(0)
                    .errorMessage(e.getMessage())
                    .fileKey(tempFileKey);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public int performImport(List<DragonCreate> dragons) {
        int count = 0;
        for (DragonCreate dragonCreate : dragons) {
            dragonService.save(dragonCreate);
            count++;
        }
        return count;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public ImportOperationEntity saveSuccessOperation(UserRole userRole, int count, String fileKey) {
        ImportOperationEntity operation = ImportOperationEntity.builder()
                .userRole(userRole)
                .status(ImportStatus.SUCCESS)
                .addedCount(count)
                .fileKey(fileKey)
                .build();
        return importOperationRepository.save(operation);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public ImportOperationEntity saveFailedOperation(UserRole userRole, String errorMessage, String fileKey) {
        ImportOperationEntity operation = ImportOperationEntity.builder()
                .userRole(userRole)
                .status(ImportStatus.FAILED)
                .addedCount(0)
                .errorMessage(errorMessage)
                .fileKey(fileKey)
                .build();
        return importOperationRepository.save(operation);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void updateImportOperationFileKey(int operationId, String fileKey) {
        importOperationRepository.updateFileKeyById((long) operationId, fileKey);
    }

    public List<ImportOperation> getImportHistory(UserRole userRole) {
        List<ImportOperationEntity> operations;

        if (userRole == UserRole.ADMIN) {
            operations = importOperationRepository.findAllByOrderByCreatedAtDesc();
        } else {
            operations = importOperationRepository.findByUserRoleOrderByCreatedAtDesc(userRole);
        }

        return operations.stream()
                .map(this::mapToImportOperation)
                .toList();
    }

    public InputStream getFile(String fileKey) {
        return fileStorageService.getFile(fileKey);
    }

    private ImportOperation mapToImportOperation(ImportOperationEntity entity) {
        ImportOperation operation = new ImportOperation()
                .id(entity.getId().intValue())
                .status(entity.getStatus() == ImportStatus.SUCCESS
                        ? ImportOperationStatus.SUCCESS
                        : ImportOperationStatus.FAILED)
                .userRole(entity.getUserRole() == UserRole.ADMIN
                        ? se.ifmo.gen.model.UserRole.ADMIN
                        : se.ifmo.gen.model.UserRole.USER)
                .createdAt(entity.getCreatedAt());

        if (entity.getAddedCount() != null) {
            operation.setAddedCount(entity.getAddedCount());
        }
        if (entity.getErrorMessage() != null) {
            operation.setErrorMessage(entity.getErrorMessage());
        }
        if (entity.getFileKey() != null) {
            operation.setFileKey(entity.getFileKey());
        }

        return operation;
    }
}
