package se.ifmo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.models.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    long countByPassportId(String passportId);
    boolean existsByPassportId(String passportId);
}
