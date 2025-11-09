package se.ifmo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import se.ifmo.gen.model.DragonCave;
import se.ifmo.models.DragonCaveEntity;
import se.ifmo.repositories.DragonCaveRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DragonCaveService Unit Tests")
class DragonCaveServiceTest {

    @Mock
    private DragonCaveRepository dragonCaveRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DragonCaveService dragonCaveService;

    @BeforeEach
    void setUp() {
        dragonCaveService = new DragonCaveService(dragonCaveRepository, modelMapper);
    }

    @Test
    @DisplayName("Should return list of DragonCave when getAll is called")
    void getAll_ShouldReturnListOfDragonCave() {
        DragonCaveEntity entity1 = DragonCaveEntity.builder()
                .id(1L)
                .depth(10.5f)
                .numberOfTreasures(100L)
                .build();
        DragonCaveEntity entity2 = DragonCaveEntity.builder()
                .id(2L)
                .depth(20.0f)
                .numberOfTreasures(200L)
                .build();

        DragonCave dto1 = new DragonCave();
        dto1.setId(1);
        dto1.setDepth(10.5f);
        dto1.setNumberOfTreasures(100L);

        DragonCave dto2 = new DragonCave();
        dto2.setId(2);
        dto2.setDepth(20.0f);
        dto2.setNumberOfTreasures(200L);

        when(dragonCaveRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(modelMapper.map(entity1, DragonCave.class)).thenReturn(dto1);
        when(modelMapper.map(entity2, DragonCave.class)).thenReturn(dto2);

        List<DragonCave> result = dragonCaveService.getAll();

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
        verify(dragonCaveRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return Optional with DragonCave when getCaveWithMaxDepth finds a cave")
    void getCaveWithMaxDepth_WhenCaveExists_ShouldReturnOptionalWithDragonCave() {
        DragonCaveEntity entity = DragonCaveEntity.builder()
                .id(1L)
                .depth(50.0f)
                .numberOfTreasures(500L)
                .build();

        DragonCave dto = new DragonCave();
        dto.setId(1);
        dto.setDepth(50.0f);
        dto.setNumberOfTreasures(500L);

        when(dragonCaveRepository.findCaveWithMaxDepth()).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity, DragonCave.class)).thenReturn(dto);

        Optional<DragonCave> result = dragonCaveService.getCaveWithMaxDepth();

        assertTrue(result.isPresent());
        assertEquals(dto, result.get());
        verify(dragonCaveRepository, times(1)).findCaveWithMaxDepth();
    }
}

