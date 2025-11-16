package se.ifmo.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.ifmo.gen.model.Location;
import se.ifmo.repositories.LocationRepository;

@Service
@RequiredArgsConstructor
public class LocationService {

  private final LocationRepository locationRepository;
  private final ModelMapper modelMapper;

  public List<Location> getAll() {
    return locationRepository.findAll().stream()
        .map(locationEntity -> modelMapper.map(locationEntity, Location.class))
        .toList();
  }
}
