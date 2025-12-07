package se.ifmo.services;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
public class ImportService {
    private final DragonService dragonService;
    private final ImportOperationRepository importOperationRepository;
    private final ObjectMapper objectMapper;

    public ImportDragonResponse importDragonsFromFile(MultipartFile file, UserRole userRole) {
        try {
            List<DragonCreate> dragons = objectMapper.readValue(
                    file.getInputStream(),
                    new TypeReference<List<DragonCreate>>() {}
            );
            return importDragons(dragons, userRole);
        } catch (IOException e) {
            ImportOperationEntity saved = saveFailedOperation(userRole, "Failed to parse JSON file: " + e.getMessage());
            return new ImportDragonResponse()
                    .operationId(saved.getId().intValue())
                    .status(ImportOperationStatus.FAILED)
                    .addedCount(0)
                    .errorMessage("Failed to parse JSON file: " + e.getMessage());
        }
    }

    public ImportDragonResponse importDragons(List<DragonCreate> dragons, UserRole userRole) {
        try {
            int count = performImport(dragons);
            ImportOperationEntity saved = saveSuccessOperation(userRole, count);

            return new ImportDragonResponse()
                    .operationId(saved.getId().intValue())
                    .status(ImportOperationStatus.SUCCESS)
                    .addedCount(count);

        } catch (Exception e) {
            ImportOperationEntity saved = saveFailedOperation(userRole, e.getMessage());

            return new ImportDragonResponse()
                    .operationId(saved.getId().intValue())
                    .status(ImportOperationStatus.FAILED)
                    .addedCount(0)
                    .errorMessage(e.getMessage());
        }
    }

    @Transactional
    public int performImport(List<DragonCreate> dragons) {
        int count = 0;
        for (DragonCreate dragonCreate : dragons) {
            dragonService.save(dragonCreate);
            count++;
        }
        return count;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ImportOperationEntity saveSuccessOperation(UserRole userRole, int count) {
        ImportOperationEntity operation = ImportOperationEntity.builder()
                .userRole(userRole)
                .status(ImportStatus.SUCCESS)
                .addedCount(count)
                .build();
        return importOperationRepository.save(operation);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ImportOperationEntity saveFailedOperation(UserRole userRole, String errorMessage) {
        ImportOperationEntity operation = ImportOperationEntity.builder()
                .userRole(userRole)
                .status(ImportStatus.FAILED)
                .addedCount(0)
                .errorMessage(errorMessage)
                .build();
        return importOperationRepository.save(operation);
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

        return operation;
    }
}
