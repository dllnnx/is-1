package se.ifmo.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.ifmo.gen.model.DragonCave;
import se.ifmo.repositories.DragonCaveRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DragonCaveService {
    private final DragonCaveRepository dragonCaveRepository;
    private final ModelMapper modelMapper;

    public List<DragonCave> getAll() {
        return dragonCaveRepository.findAll()
                .stream()
                .map(dragonCaveEntity -> modelMapper.map(dragonCaveEntity, DragonCave.class))
                .toList();
    }

    public Optional<DragonCave> getCaveWithMaxDepth() {
        return dragonCaveRepository.findCaveWithMaxDepth()
                .map(dragonCaveEntity -> modelMapper.map(dragonCaveEntity, DragonCave.class));
    }
}
