package se.ifmo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.models.ImportOperationEntity;
import se.ifmo.models.UserRole;

@Repository
public interface ImportOperationRepository extends JpaRepository<ImportOperationEntity, Long> {
    List<ImportOperationEntity> findByUserRole(UserRole userRole);
    
    List<ImportOperationEntity> findAllByOrderByCreatedAtDesc();
    
    List<ImportOperationEntity> findByUserRoleOrderByCreatedAtDesc(UserRole userRole);
}

