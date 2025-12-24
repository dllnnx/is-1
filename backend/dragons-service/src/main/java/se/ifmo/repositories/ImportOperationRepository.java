package se.ifmo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.ifmo.models.ImportOperationEntity;
import se.ifmo.models.UserRole;

@Repository
public interface ImportOperationRepository extends JpaRepository<ImportOperationEntity, Long> {
    List<ImportOperationEntity> findByUserRole(UserRole userRole);
    
    List<ImportOperationEntity> findAllByOrderByCreatedAtDesc();
    
    List<ImportOperationEntity> findByUserRoleOrderByCreatedAtDesc(UserRole userRole);
    
    @Modifying
    @Transactional
    @Query("UPDATE ImportOperationEntity i SET i.fileKey = ?2 WHERE i.id = ?1")
    void updateFileKeyById(Long id, String fileKey);
}
