package se.ifmo.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.ifmo.models.DragonCaveEntity;

@Repository
public interface DragonCaveRepository extends JpaRepository<DragonCaveEntity, Long> {

  @Query(
      "SELECT c FROM DragonCaveEntity c WHERE c.depth = (SELECT MAX(c2.depth) FROM DragonCaveEntity c2)")
  Optional<DragonCaveEntity> findCaveWithMaxDepth();
}
