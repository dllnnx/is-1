package se.ifmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.models.LocationEntity;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}