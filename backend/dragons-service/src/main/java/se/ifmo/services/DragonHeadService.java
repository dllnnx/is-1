package se.ifmo.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import se.ifmo.gen.model.DragonHead;
import se.ifmo.repositories.DragonHeadRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DragonHeadService {
    private final DragonHeadRepository dragonHeadRepository;
    private final ModelMapper modelMapper;

    public List<DragonHead> getAll() {
        return dragonHeadRepository.findAll()
                .stream()
                .map(dragonHeadEntity -> modelMapper.map(dragonHeadEntity, DragonHead.class))
                .toList();
    }
}
