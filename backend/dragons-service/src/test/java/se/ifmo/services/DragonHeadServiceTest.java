package se.ifmo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import se.ifmo.gen.model.DragonHead;
import se.ifmo.models.DragonHeadEntity;
import se.ifmo.repositories.DragonHeadRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("DragonHeadService Unit Tests")
class DragonHeadServiceTest {

  @Mock private DragonHeadRepository dragonHeadRepository;

  @Mock private ModelMapper modelMapper;

  @InjectMocks private DragonHeadService dragonHeadService;

  @BeforeEach
  void setUp() {
    dragonHeadService = new DragonHeadService(dragonHeadRepository, modelMapper);
  }

  @Test
  @DisplayName("Should return empty list when repository returns empty list")
  void getAll_WhenRepositoryReturnsEmptyList_ShouldReturnEmptyList() {
    List<DragonHeadEntity> emptyEntityList = new ArrayList<>();
    when(dragonHeadRepository.findAll()).thenReturn(emptyEntityList);

    List<DragonHead> result = dragonHeadService.getAll();

    assertNotNull(result);
    assertTrue(result.isEmpty());
    verify(dragonHeadRepository, times(1)).findAll();
    verify(modelMapper, never()).map(any(), eq(DragonHead.class));
  }

  @Test
  @DisplayName("Should return list with single DragonHead when repository returns single entity")
  void getAll_WhenRepositoryReturnsSingleEntity_ShouldReturnSingleDragonHead() {
    DragonHeadEntity entity = DragonHeadEntity.builder().id(1L).toothCount(10.5f).build();

    DragonHead dto = new DragonHead();
    dto.setId(1);
    dto.setToothCount(10.5f);

    List<DragonHeadEntity> entityList = List.of(entity);
    when(dragonHeadRepository.findAll()).thenReturn(entityList);
    when(modelMapper.map(entity, DragonHead.class)).thenReturn(dto);

    List<DragonHead> result = dragonHeadService.getAll();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(dto, result.get(0));
    verify(dragonHeadRepository, times(1)).findAll();
    verify(modelMapper, times(1)).map(entity, DragonHead.class);
  }

  @Test
  @DisplayName(
      "Should return list with multiple DragonHeads when repository returns multiple entities")
  void getAll_WhenRepositoryReturnsMultipleEntities_ShouldReturnMultipleDragonHeads() {
    DragonHeadEntity entity1 = DragonHeadEntity.builder().id(1L).toothCount(10.5f).build();

    DragonHeadEntity entity2 = DragonHeadEntity.builder().id(2L).toothCount(20.0f).build();

    DragonHeadEntity entity3 = DragonHeadEntity.builder().id(3L).toothCount(15.75f).build();

    DragonHead dto1 = new DragonHead();
    dto1.setId(1);
    dto1.setToothCount(10.5f);

    DragonHead dto2 = new DragonHead();
    dto2.setId(2);
    dto2.setToothCount(20.0f);

    DragonHead dto3 = new DragonHead();
    dto3.setId(3);
    dto3.setToothCount(15.75f);

    List<DragonHeadEntity> entityList = List.of(entity1, entity2, entity3);
    when(dragonHeadRepository.findAll()).thenReturn(entityList);
    when(modelMapper.map(entity1, DragonHead.class)).thenReturn(dto1);
    when(modelMapper.map(entity2, DragonHead.class)).thenReturn(dto2);
    when(modelMapper.map(entity3, DragonHead.class)).thenReturn(dto3);

    List<DragonHead> result = dragonHeadService.getAll();

    assertNotNull(result);
    assertEquals(3, result.size());
    assertEquals(dto1, result.get(0));
    assertEquals(dto2, result.get(1));
    assertEquals(dto3, result.get(2));
    verify(dragonHeadRepository, times(1)).findAll();
    verify(modelMapper, times(1)).map(entity1, DragonHead.class);
    verify(modelMapper, times(1)).map(entity2, DragonHead.class);
    verify(modelMapper, times(1)).map(entity3, DragonHead.class);
  }

  @Test
  @DisplayName("Should map entity with null toothCount correctly")
  void getAll_WhenEntityHasNullToothCount_ShouldMapCorrectly() {
    DragonHeadEntity entity = DragonHeadEntity.builder().id(1L).toothCount(null).build();

    DragonHead dto = new DragonHead();
    dto.setId(1);
    dto.setToothCount(null);

    List<DragonHeadEntity> entityList = List.of(entity);
    when(dragonHeadRepository.findAll()).thenReturn(entityList);
    when(modelMapper.map(entity, DragonHead.class)).thenReturn(dto);

    List<DragonHead> result = dragonHeadService.getAll();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertNull(result.get(0).getToothCount());
    verify(dragonHeadRepository, times(1)).findAll();
    verify(modelMapper, times(1)).map(entity, DragonHead.class);
  }

  @Test
  @DisplayName("Should call repository findAll exactly once")
  void getAll_ShouldCallRepositoryFindAllExactlyOnce() {
    List<DragonHeadEntity> entityList =
        List.of(DragonHeadEntity.builder().id(1L).toothCount(10.0f).build());
    when(dragonHeadRepository.findAll()).thenReturn(entityList);
    when(modelMapper.map(any(DragonHeadEntity.class), eq(DragonHead.class)))
        .thenReturn(new DragonHead());

    dragonHeadService.getAll();

    verify(dragonHeadRepository, times(1)).findAll();
    verifyNoMoreInteractions(dragonHeadRepository);
  }

  @Test
  @DisplayName("Should map each entity using ModelMapper")
  void getAll_ShouldMapEachEntityUsingModelMapper() {
    DragonHeadEntity entity1 = DragonHeadEntity.builder().id(1L).toothCount(10.0f).build();
    DragonHeadEntity entity2 = DragonHeadEntity.builder().id(2L).toothCount(20.0f).build();

    List<DragonHeadEntity> entityList = List.of(entity1, entity2);
    when(dragonHeadRepository.findAll()).thenReturn(entityList);
    when(modelMapper.map(any(DragonHeadEntity.class), eq(DragonHead.class)))
        .thenReturn(new DragonHead());

    dragonHeadService.getAll();

    verify(modelMapper, times(2)).map(any(DragonHeadEntity.class), eq(DragonHead.class));
    verify(modelMapper, times(1)).map(entity1, DragonHead.class);
    verify(modelMapper, times(1)).map(entity2, DragonHead.class);
  }
}
