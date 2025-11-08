package se.ifmo.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.ifmo.gen.model.Coordinates;
import se.ifmo.repositories.CoordinatesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordinatesService {

    private final CoordinatesRepository coordinatesRepository;
    private final ModelMapper modelMapper;

    public List<Coordinates> getAll() {
        return coordinatesRepository.findAll()
                .stream()
                .map(coordinatesEntity -> modelMapper.map(coordinatesEntity, Coordinates.class))
                .toList();
    }
}
