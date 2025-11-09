package se.ifmo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import se.ifmo.gen.model.Coordinates;
import se.ifmo.models.CoordinatesEntity;
import se.ifmo.repositories.CoordinatesRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CoordinatesService Unit Tests")
class CoordinatesServiceTest {

    @Mock
    private CoordinatesRepository coordinatesRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CoordinatesService coordinatesService;

    @BeforeEach
    void setUp() {
        coordinatesService = new CoordinatesService(coordinatesRepository, modelMapper);
    }

    @Test
    @DisplayName("Should return list of Coordinates when getAll is called")
    void getAll_ShouldReturnListOfCoordinates() {
        CoordinatesEntity entity1 = CoordinatesEntity.builder()
                .id(1L)
                .x(10.5f)
                .y(20.0)
                .build();
        CoordinatesEntity entity2 = CoordinatesEntity.builder()
                .id(2L)
                .x(30.0f)
                .y(40.0)
                .build();

        Coordinates dto1 = new Coordinates();
        dto1.setX(10.5f);
        dto1.setY(20.0);

        Coordinates dto2 = new Coordinates();
        dto2.setX(30.0f);
        dto2.setY(40.0);

        when(coordinatesRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(modelMapper.map(entity1, Coordinates.class)).thenReturn(dto1);
        when(modelMapper.map(entity2, Coordinates.class)).thenReturn(dto2);

        List<Coordinates> result = coordinatesService.getAll();

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
        verify(coordinatesRepository, times(1)).findAll();
    }
}

