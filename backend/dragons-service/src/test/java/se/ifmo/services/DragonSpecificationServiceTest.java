package se.ifmo.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import se.ifmo.models.DragonEntity;

@DisplayName("DragonSpecificationService Unit Tests")
class DragonSpecificationServiceTest {

  private DragonSpecificationService specificationService;

  @BeforeEach
  void setUp() {
    specificationService = new DragonSpecificationService();
  }

  @Test
  @DisplayName("Should build specification with null filter")
  void buildSpecification_WithNullFilter_ShouldReturnConjunction() {
    Specification<DragonEntity> spec = specificationService.buildSpecification(null);
    assertNotNull(spec);
  }

  @Test
  @DisplayName("Should build sort with null sorting list")
  void buildSort_WithNullSortingList_ShouldReturnDefaultSort() {
    Sort sort = specificationService.buildSort(null);
    assertNotNull(sort);
    assertEquals(Sort.Direction.ASC, sort.getOrderFor("id").getDirection());
  }
}
