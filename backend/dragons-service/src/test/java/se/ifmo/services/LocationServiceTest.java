package se.ifmo.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.openapitools.jackson.nullable.JsonNullable;
import se.ifmo.gen.model.Location;
import se.ifmo.models.LocationEntity;
import se.ifmo.repositories.LocationRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("LocationService Unit Tests")
class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        locationService = new LocationService(locationRepository, modelMapper);
    }

    @Test
    @DisplayName("Should return list of Location when getAll is called")
    void getAll_ShouldReturnListOfLocation() {
        LocationEntity entity1 = LocationEntity.builder()
                .id(1L)
                .x(10.5)
                .y(20)
                .name("Location 1")
                .build();
        LocationEntity entity2 = LocationEntity.builder()
                .id(2L)
                .x(30.0)
                .y(40)
                .name("Location 2")
                .build();

        Location dto1 = new Location();
        dto1.setX(10.5);
        dto1.setY(20);
        dto1.setName(JsonNullable.of("Location 1"));

        Location dto2 = new Location();
        dto2.setX(30.0);
        dto2.setY(40);
        dto2.setName(JsonNullable.of("Location 2"));

        when(locationRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(modelMapper.map(entity1, Location.class)).thenReturn(dto1);
        when(modelMapper.map(entity2, Location.class)).thenReturn(dto2);

        List<Location> result = locationService.getAll();

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
        verify(locationRepository, times(1)).findAll();
    }
}

