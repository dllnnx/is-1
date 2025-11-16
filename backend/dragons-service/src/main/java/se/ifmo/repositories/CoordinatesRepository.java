package se.ifmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.models.CoordinatesEntity;

@Repository
public interface CoordinatesRepository extends JpaRepository<CoordinatesEntity, Long> {}
