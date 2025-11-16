package se.ifmo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import se.ifmo.gen.model.*;
import se.ifmo.models.*;
import se.ifmo.repositories.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DragonService Unit Tests")
class DragonServiceTest {

  @Mock private DragonRepository dragonRepository;

  @Mock private PersonRepository personRepository;

  @Mock private CoordinatesRepository coordinatesRepository;

  @Mock private LocationRepository locationRepository;

  @Mock private DragonCaveRepository dragonCaveRepository;

  @Mock private DragonHeadRepository dragonHeadRepository;

  @Mock private DragonCaveService dragonCaveService;

  @Mock private DragonSpecificationService specificationService;

  @Mock private ModelMapper modelMapper;

  @InjectMocks private DragonService dragonService;

  @BeforeEach
  void setUp() {
    dragonService =
        new DragonService(
            dragonRepository,
            personRepository,
            locationRepository,
            coordinatesRepository,
            dragonCaveRepository,
            dragonHeadRepository,
            dragonCaveService,
            specificationService,
            modelMapper);
  }

  @Test
  @DisplayName("Should return list of Dragon when getAll is called")
  void getAll_ShouldReturnListOfDragon() {
    DragonEntity entity = DragonEntity.builder().id(1L).name("Dragon1").age(100).build();
    Dragon dto = new Dragon();
    dto.setId(1);
    dto.setName("Dragon1");
    dto.setAge(100);

    when(dragonRepository.findAll()).thenReturn(List.of(entity));
    when(modelMapper.map(entity, Dragon.class)).thenReturn(dto);

    List<Dragon> result = dragonService.getAll();

    assertEquals(1, result.size());
    verify(dragonRepository, times(1)).findAll();
  }

  @Test
  @DisplayName(
      "Should return filtered dragons when getDragonsWithFilters is called with pagination")
  void getDragonsWithFilters_WithPagination_ShouldReturnFilteredDragons() {
    GetDragonsRequest request = new GetDragonsRequest();
    Pagination pagination = new Pagination();
    pagination.setPage(0);
    pagination.setSize(10);
    request.setPagination(pagination);

    DragonEntity entity = DragonEntity.builder().id(1L).name("Dragon1").age(100).build();
    Dragon dto = new Dragon();
    dto.setId(1);
    dto.setName("Dragon1");

    Specification<DragonEntity> spec = (root, query, cb) -> cb.conjunction();
    Sort sort = Sort.by("id");

    when(specificationService.buildSpecification(any())).thenReturn(spec);
    when(specificationService.buildSort(any())).thenReturn(sort);
    when(dragonRepository.findAll(any(Specification.class), any(PageRequest.class)))
        .thenReturn(new PageImpl<>(List.of(entity)));
    when(modelMapper.map(entity, Dragon.class)).thenReturn(dto);

    GetDragons200Response result = dragonService.getDragonsWithFilters(request);

    assertEquals(1, result.getDragons().size());
    verify(dragonRepository, times(1)).findAll(any(Specification.class), any(PageRequest.class));
  }

  @Test
  @DisplayName("Should return Optional with Dragon when getDragon finds a dragon")
  void getDragon_WhenDragonExists_ShouldReturnOptionalWithDragon() {
    DragonEntity entity = DragonEntity.builder().id(1L).name("Dragon1").age(100).build();
    Dragon dto = new Dragon();
    dto.setId(1);
    dto.setName("Dragon1");

    when(dragonRepository.findById(1L)).thenReturn(Optional.of(entity));
    when(modelMapper.map(entity, Dragon.class)).thenReturn(dto);

    Optional<Dragon> result = dragonService.getDragon(1);

    assertTrue(result.isPresent());
    assertEquals(dto, result.get());
    verify(dragonRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("Should return Optional with DragonEntity when findById finds a dragon")
  void findById_WhenDragonExists_ShouldReturnOptionalWithDragonEntity() {
    DragonEntity entity = DragonEntity.builder().id(1L).name("Dragon1").age(100).build();
    when(dragonRepository.findById(1L)).thenReturn(Optional.of(entity));

    Optional<DragonEntity> result = dragonService.findById(1L);

    assertTrue(result.isPresent());
    assertEquals(entity, result.get());
    verify(dragonRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("Should return sum of ages when getAgesSum is called")
  void getAgesSum_ShouldReturnSumOfAges() {
    DragonEntity entity1 = DragonEntity.builder().id(1L).age(100).build();
    DragonEntity entity2 = DragonEntity.builder().id(2L).age(200).build();

    when(dragonRepository.findAll()).thenReturn(List.of(entity1, entity2));

    int result = dragonService.getAgesSum();

    assertEquals(300, result);
    verify(dragonRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Should return Optional with max type dragon when getMaxTypeDragon is called")
  void getMaxTypeDragon_ShouldReturnMaxTypeDragon() {
    DragonEntity entity1 =
        DragonEntity.builder().id(1L).type(se.ifmo.models.DragonType.WATER).build();
    DragonEntity entity2 =
        DragonEntity.builder().id(2L).type(se.ifmo.models.DragonType.FIRE).build();

    Dragon dto = new Dragon();
    dto.setId(2);
    dto.setType(se.ifmo.gen.model.DragonType.FIRE);

    when(dragonRepository.findAll()).thenReturn(List.of(entity1, entity2));
    when(modelMapper.map(entity2, Dragon.class)).thenReturn(dto);

    Optional<Dragon> result = dragonService.getMaxTypeDragon();

    assertTrue(result.isPresent());
    verify(dragonRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("Should save dragon when save is called")
  void save_ShouldSaveDragon() {
    DragonCreate dragonCreate = new DragonCreate();
    dragonCreate.setName("Dragon1");
    dragonCreate.setAge(100);

    DragonEntity entity = DragonEntity.builder().id(1L).name("Dragon1").age(100).build();

    when(modelMapper.map(dragonCreate, DragonEntity.class)).thenReturn(entity);
    when(dragonRepository.save(entity)).thenReturn(entity);

    DragonEntity result = dragonService.save(dragonCreate);

    assertNotNull(result);
    verify(dragonRepository, times(1)).save(entity);
  }

  @Test
  @DisplayName("Should return true when existsById finds a dragon")
  void existsById_WhenDragonExists_ShouldReturnTrue() {
    when(dragonRepository.existsById(1L)).thenReturn(true);

    boolean result = dragonService.existsById(1L);

    assertTrue(result);
    verify(dragonRepository, times(1)).existsById(1L);
  }

  @Test
  @DisplayName("Should delete dragon when deleteById is called")
  void deleteById_ShouldDeleteDragon() {
    doNothing().when(dragonRepository).deleteById(1L);

    dragonService.deleteById(1L);

    verify(dragonRepository, times(1)).deleteById(1L);
  }

  @Test
  @DisplayName("Should return Optional with Dragon when findDragonByAge finds a dragon")
  void findDragonByAge_WhenDragonExists_ShouldReturnOptionalWithDragon() {
    DragonEntity entity = DragonEntity.builder().id(1L).age(100).build();
    Dragon dto = new Dragon();
    dto.setId(1);
    dto.setAge(100);

    when(dragonRepository.findFirstByAge(100)).thenReturn(Optional.of(entity));
    when(modelMapper.map(entity, Dragon.class)).thenReturn(dto);

    Optional<Dragon> result = dragonService.findDragonByAge(100);

    assertTrue(result.isPresent());
    assertEquals(dto, result.get());
    verify(dragonRepository, times(1)).findFirstByAge(100);
  }

  @Test
  @DisplayName("Should return true when deleteByAge finds and deletes a dragon")
  void deleteByAge_WhenDragonExists_ShouldReturnTrue() {
    DragonEntity entity = DragonEntity.builder().id(1L).age(100).build();

    when(dragonRepository.findFirstByAge(100)).thenReturn(Optional.of(entity));
    doNothing().when(dragonRepository).delete(entity);

    boolean result = dragonService.deleteByAge(100);

    assertTrue(result);
    verify(dragonRepository, times(1)).findFirstByAge(100);
    verify(dragonRepository, times(1)).delete(entity);
  }

  @Test
  @DisplayName("Should return count when deleteDragonsInCaveWithMaxDepth deletes dragons")
  void deleteDragonsInCaveWithMaxDepth_ShouldReturnDeletedCount() {
    DragonCave cave = new DragonCave();
    cave.setId(1);
    DragonEntity entity = DragonEntity.builder().id(1L).build();

    when(dragonCaveService.getCaveWithMaxDepth()).thenReturn(Optional.of(cave));
    when(dragonRepository.findAllByCaveId(1)).thenReturn(List.of(entity));
    doNothing().when(dragonRepository).deleteAll(anyList());

    int result = dragonService.deleteDragonsInCaveWithMaxDepth();

    assertEquals(1, result);
    verify(dragonCaveService, times(1)).getCaveWithMaxDepth();
    verify(dragonRepository, times(1)).findAllByCaveId(1);
  }

  @Test
  @DisplayName("Should return true when killDragon successfully sets killer")
  void killDragon_WhenBothExist_ShouldReturnTrue() {
    DragonEntity dragonEntity = DragonEntity.builder().id(1L).build();
    PersonEntity personEntity = PersonEntity.builder().id(1L).build();

    when(dragonRepository.findById(1L)).thenReturn(Optional.of(dragonEntity));
    when(personRepository.findById(1L)).thenReturn(Optional.of(personEntity));
    when(dragonRepository.save(dragonEntity)).thenReturn(dragonEntity);

    boolean result = dragonService.killDragon(1L, 1L);

    assertTrue(result);
    verify(dragonRepository, times(1)).findById(1L);
    verify(personRepository, times(1)).findById(1L);
    verify(dragonRepository, times(1)).save(dragonEntity);
  }
}
