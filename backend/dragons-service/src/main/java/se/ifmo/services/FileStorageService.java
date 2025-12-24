package se.ifmo.services;

import io.minio.*;
import io.minio.errors.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.ifmo.configurations.MinioProperties;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public String getBucketName() {
        return minioProperties.getBucketName();
    }

    @PostConstruct
    public void initializeBucket() {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                    .bucket(getBucketName())
                    .build());
            
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(getBucketName())
                        .build());
                log.info("Created minio bucket: {}", getBucketName());
            } else {
                log.info("minio bucket already exists: {}", getBucketName());
            }
        } catch (Exception e) {
            log.error("Failed to initialize minio bucket", e);
            throw new RuntimeException("Failed to initialize minio bucket", e);
        }
    }

    public String prepareFileStorage(MultipartFile file) {
        try {
            String tempFileKey = "temp/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(getBucketName())
                    .object(tempFileKey)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
            
            log.info("Prepared file storage in minio with temporary key: {}", tempFileKey);
            return tempFileKey;
        } catch (Exception e) {
            log.error("Failed to prepare file storage in minio", e);
            throw new RuntimeException("Failed to prepare file storage in minio", e);
        }
    }

    public String commitFileStorage(String tempFileKey) {
        try {
            String originalFilename = tempFileKey.substring(tempFileKey.lastIndexOf("_") + 1);
            String permanentFileKey = UUID.randomUUID() + "_" + originalFilename;
            
            minioClient.copyObject(CopyObjectArgs.builder()
                    .bucket(getBucketName())
                    .object(permanentFileKey)
                    .source(CopySource.builder()
                            .bucket(getBucketName())
                            .object(tempFileKey)
                            .build())
                    .build());
            
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(getBucketName())
                    .object(tempFileKey)
                    .build());
            
            log.info("Committed file storage in minio. Temporary key: {} -> Permanent key: {}", tempFileKey, permanentFileKey);
            return permanentFileKey;
        } catch (Exception e) {
            log.error("Failed to commit file storage in minio", e);
            throw new RuntimeException("Failed to commit file storage in minio", e);
        }
    }

    public void rollbackFileStorage(String tempFileKey) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(getBucketName())
                    .object(tempFileKey)
                    .build());
            
            log.info("Rolled back file storage in minio. Deleted temporary key: {}", tempFileKey);
        } catch (Exception e) {
            log.error("Failed to rollback file storage in minio for key: {}", tempFileKey, e);
        }
    }

    public GetObjectResponse getFile(String fileKey) {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(getBucketName())
                    .object(fileKey)
                    .build());
        } catch (Exception e) {
            log.error("Failed to retrieve file from minio with key: {}", fileKey, e);
            throw new RuntimeException("Failed to retrieve file from minio", e);
        }
    }
}
