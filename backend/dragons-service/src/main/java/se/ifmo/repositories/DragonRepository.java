package se.ifmo.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import se.ifmo.models.CoordinatesEntity;
import se.ifmo.models.DragonEntity;

@Repository
public interface DragonRepository
    extends JpaRepository<DragonEntity, Long>, JpaSpecificationExecutor<DragonEntity> {
  Optional<DragonEntity> findFirstByAge(Integer age);

  List<DragonEntity> findAllByCaveId(Integer caveId);

    boolean existsByName(String name);

    boolean existsByCoordinatesEntity(CoordinatesEntity coordinatesEntity);
}
