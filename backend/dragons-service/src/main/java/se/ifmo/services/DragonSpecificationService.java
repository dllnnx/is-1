package se.ifmo.services;

import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import se.ifmo.gen.model.*;
import se.ifmo.models.*;

@Service
public class DragonSpecificationService {

  public Specification<DragonEntity> buildSpecification(DragonFilter filter) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (filter == null) {
        return criteriaBuilder.conjunction();
      }

      if (filter.getId() != null) {
        predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId().longValue()));
      }

      if (filter.getName() != null && !filter.getName().isEmpty()) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%" + filter.getName().toLowerCase() + "%"));
      }

      if (filter.getCreationDateRange() != null) {
        addCreationDatePredicates(filter.getCreationDateRange(), root, criteriaBuilder, predicates);
      }

      if (filter.getAge() != null) {
        predicates.add(criteriaBuilder.equal(root.get("age"), filter.getAge()));
      }

      if (filter.getColor() != null) {
        predicates.add(
            criteriaBuilder.equal(
                root.get("color"), se.ifmo.models.Color.valueOf(filter.getColor().getValue())));
      }

      if (filter.getType() != null) {
        predicates.add(
            criteriaBuilder.equal(
                root.get("type"), se.ifmo.models.DragonType.valueOf(filter.getType().getValue())));
      }

      if (filter.getCharacter() != null && filter.getCharacter().isPresent()) {
        predicates.add(
            criteriaBuilder.equal(
                root.get("character"),
                se.ifmo.models.DragonCharacter.valueOf(filter.getCharacter().get().getValue())));
      }

      addCoordinatesPredicates(filter, root, criteriaBuilder, predicates);
      addCavePredicates(filter, root, criteriaBuilder, predicates);
      addHeadPredicates(filter, root, criteriaBuilder, predicates);
      addKillerPredicates(filter, root, criteriaBuilder, predicates);

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }

  private void addCoordinatesPredicates(
      DragonFilter filter,
      Root<DragonEntity> root,
      CriteriaBuilder criteriaBuilder,
      List<Predicate> predicates) {
    if (filter.getCoordinates() != null) {
      Join<DragonEntity, CoordinatesEntity> coordJoin = root.join("coordinatesEntity");
      CoordinatesFilter coordFilter = filter.getCoordinates();

      if (coordFilter.getX() != null) {
        predicates.add(criteriaBuilder.equal(coordJoin.get("x"), coordFilter.getX()));
      }

      if (coordFilter.getY() != null) {
        predicates.add(criteriaBuilder.equal(coordJoin.get("y"), coordFilter.getY()));
      }
    }
  }

  private void addCreationDatePredicates(
      DateRangeFilter dateRange,
      Root<DragonEntity> root,
      CriteriaBuilder criteriaBuilder,
      List<Predicate> predicates) {
    if (dateRange.getFrom() != null) {
      predicates.add(
          criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), dateRange.getFrom()));
    }

    if (dateRange.getTo() != null) {
      predicates.add(
          criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), dateRange.getTo()));
    }
  }

  private void addCavePredicates(
      DragonFilter filter,
      Root<DragonEntity> root,
      CriteriaBuilder criteriaBuilder,
      List<Predicate> predicates) {
    if (filter.getCave() != null) {
      Join<DragonEntity, DragonCaveEntity> caveJoin = root.join("cave");
      DragonCaveFilter caveFilter = filter.getCave();

      if (caveFilter.getDepth() != null) {
        predicates.add(criteriaBuilder.equal(caveJoin.get("depth"), caveFilter.getDepth()));
      }

      if (caveFilter.getNumberOfTreasures() != null) {
        predicates.add(
            criteriaBuilder.equal(
                caveJoin.get("numberOfTreasures"), caveFilter.getNumberOfTreasures()));
      }
    }
  }

  private void addHeadPredicates(
      DragonFilter filter,
      Root<DragonEntity> root,
      CriteriaBuilder criteriaBuilder,
      List<Predicate> predicates) {
    if (filter.getHead() != null) {
      Join<DragonEntity, DragonHeadEntity> headJoin = root.join("head", JoinType.LEFT);
      DragonHeadFilter headFilter = filter.getHead();

      if (headFilter.getToothCount() != null) {
        predicates.add(
            criteriaBuilder.equal(headJoin.get("toothCount"), headFilter.getToothCount()));
      }
    }
  }

  private void addKillerPredicates(
      DragonFilter filter,
      Root<DragonEntity> root,
      CriteriaBuilder criteriaBuilder,
      List<Predicate> predicates) {
    if (filter.getKiller() != null
        && filter.getKiller().isPresent()
        && filter.getKiller().get() != null) {
      Join<DragonEntity, PersonEntity> killerJoin = root.join("killer", JoinType.LEFT);
      PersonFilter killerFilter = filter.getKiller().get();

      if (killerFilter.getName() != null && !killerFilter.getName().isEmpty()) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(killerJoin.get("name")),
                "%" + killerFilter.getName().toLowerCase() + "%"));
      }

      if (killerFilter.getEyeColor() != null) {
        predicates.add(
            criteriaBuilder.equal(
                killerJoin.get("eyeColor"),
                se.ifmo.models.Color.valueOf(killerFilter.getEyeColor().getValue())));
      }

      if (killerFilter.getHairColor() != null && killerFilter.getHairColor().isPresent()) {
        predicates.add(
            criteriaBuilder.equal(
                killerJoin.get("hairColor"),
                se.ifmo.models.Color.valueOf(killerFilter.getHairColor().get().getValue())));
      }

      if (killerFilter.getHeight() != null && killerFilter.getHeight().isPresent()) {
        predicates.add(
            criteriaBuilder.equal(killerJoin.get("height"), killerFilter.getHeight().get()));
      }

      if (killerFilter.getWeight() != null) {
        predicates.add(criteriaBuilder.equal(killerJoin.get("weight"), killerFilter.getWeight()));
      }

      if (killerFilter.getPassportID() != null && !killerFilter.getPassportID().isEmpty()) {
        predicates.add(
            criteriaBuilder.equal(killerJoin.get("passportId"), killerFilter.getPassportID()));
      }

      addKillerLocationPredicates(killerFilter, killerJoin, criteriaBuilder, predicates);
    }
  }

  private void addKillerLocationPredicates(
      PersonFilter killerFilter,
      Join<DragonEntity, PersonEntity> killerJoin,
      CriteriaBuilder criteriaBuilder,
      List<Predicate> predicates) {
    if (killerFilter.getLocation() != null && killerFilter.getLocation().isPresent()) {
      Join<PersonEntity, LocationEntity> locationJoin =
          killerJoin.join("locationEntity", JoinType.LEFT);
      LocationFilter locationFilter = killerFilter.getLocation().get();

      if (locationFilter.getX() != null) {
        predicates.add(criteriaBuilder.equal(locationJoin.get("x"), locationFilter.getX()));
      }

      if (locationFilter.getY() != null) {
        predicates.add(criteriaBuilder.equal(locationJoin.get("y"), locationFilter.getY()));
      }

      if (locationFilter.getName() != null && locationFilter.getName().isPresent()) {
        String nameValue = locationFilter.getName().get();
        if (nameValue != null && !nameValue.isEmpty()) {
          predicates.add(
              criteriaBuilder.like(
                  criteriaBuilder.lower(locationJoin.get("name")),
                  "%" + nameValue.toLowerCase() + "%"));
        }
      }
    }
  }

  public Sort buildSort(List<Sorting> sortingList) {
    if (sortingList == null || sortingList.isEmpty()) {
      return Sort.by(Sort.Direction.ASC, "id");
    }

    List<Sort.Order> orders = new ArrayList<>();

    for (Sorting sorting : sortingList) {
      Sort.Direction direction =
          sorting.getDirection() == SortingDirection.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;

      String property = mapSortingColumn(sorting.getColumn());
      orders.add(new Sort.Order(direction, property));
    }

    return Sort.by(orders);
  }

  private String mapSortingColumn(SortingColumn column) {
    return switch (column) {
      case ID -> "id";
      case NAME -> "name";
      case COORDINATES_X -> "coordinatesEntity.x";
      case COORDINATES_Y -> "coordinatesEntity.y";
      case CREATION_DATE -> "creationDate";
      case CAVE_DEPTH -> "cave.depth";
      case CAVE_NUMBER_OF_TREASURES -> "cave.numberOfTreasures";
      case AGE -> "age";
      case COLOR -> "color";
      case DRAGON_TYPE -> "type";
      case CHARACTER -> "character";
      case HEAD_TOOTH_COUNT -> "head.toothCount";
      case PERSON_NAME -> "killer.name";
      case PERSON_EYE_COLOR -> "killer.eyeColor";
      case PERSON_HAIR_COLOR -> "killer.hairColor";
      case PERSON_LOCATION_X -> "killer.locationEntity.x";
      case PERSON_LOCATION_Y -> "killer.locationEntity.y";
      case PERSON_LOCATION_NAME -> "killer.locationEntity.name";
      case PERSON_HEIGHT -> "killer.height";
      case PERSON_WEIGHT -> "killer.weight";
      case PERSON_PASSPORT_ID -> "killer.passportId";
    };
  }
}
