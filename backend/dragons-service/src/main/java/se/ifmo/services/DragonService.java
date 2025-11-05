package se.ifmo.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ifmo.gen.model.*;
import se.ifmo.models.*;
import se.ifmo.models.Color;
import se.ifmo.models.DragonType;
import se.ifmo.repositories.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DragonService {
    private final DragonRepository dragonRepository;
    private final PersonRepository personRepository;
    private final LocationRepository locationRepository;
    private final CoordinatesRepository coordinatesRepository;
    private final DragonCaveRepository dragonCaveRepository;
    private final DragonHeadRepository dragonHeadRepository;
    private final DragonCaveService dragonCaveService;
    private final DragonSpecificationService specificationService;
    private final ModelMapper modelMapper;

    public List<Dragon> getAll() {
        return dragonRepository.findAll()
                .stream()
                .map(dragonEntity -> modelMapper.map(dragonEntity, Dragon.class))
                .toList();
    }

    public GetDragons200Response getDragonsWithFilters(GetDragonsRequest request) {
        Specification<DragonEntity> spec = specificationService.buildSpecification(request.getDragon());
        Sort sort = specificationService.buildSort(request.getSorting());
        long dragonCount = dragonRepository.count();

        if (request.getPagination() == null)
            request.setPagination(new Pagination());
        Pagination pagination = request.getPagination();
        int page = pagination.getPage() != null ? pagination.getPage() : 0;
        int size = pagination.getSize() != null ? pagination.getSize() : 20;

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<DragonEntity> dragonPage = dragonRepository.findAll(spec, pageRequest);

        return new GetDragons200Response()
                .dragons(dragonPage.getContent()
                        .stream()
                        .map(entity -> modelMapper.map(entity, Dragon.class))
                        .toList())
                .totalPageCount(dragonPage.getTotalPages())
                .totalDragonsCount((int) dragonCount);
    }

    public Optional<Dragon> getDragon(Integer id) {
        return dragonRepository.findById(id.longValue()).map(dragonEntity -> modelMapper.map(dragonEntity, Dragon.class));
    }

    public Optional<DragonEntity> findById(Long id) {
        return dragonRepository.findById(id);
    }

    public int getAgesSum() {
        return dragonRepository.findAll()
                .stream()
                .mapToInt(DragonEntity::getAge)
                .sum();
    }

    public Optional<Dragon> getMaxTypeDragon() {
        return dragonRepository.findAll()
                .stream()
                .max(Comparator.comparingInt(d -> d.getType().getPriority()))
                .map(dragonEntity -> modelMapper.map(dragonEntity, Dragon.class));
    }

    public DragonEntity save(DragonCreate dragonCreate) {
        DragonEntity dragonEntity = modelMapper.map(dragonCreate, DragonEntity.class);

        if (dragonCreate.getCoordinates() != null) {
            CoordinatesEntity coordinatesEntity;
            if (dragonCreate.getCoordinates().getId() == null) {
                coordinatesEntity = coordinatesRepository.save(
                        modelMapper.map(dragonCreate.getCoordinates(), CoordinatesEntity.class)
                );
            } else {
                coordinatesEntity = coordinatesRepository.getReferenceById(dragonCreate.getCoordinates().getId().longValue());
            }
            dragonEntity.setCoordinatesEntity(coordinatesEntity);
        }

        if (dragonCreate.getCave() != null) {
            DragonCaveEntity dragonCaveEntity;
            if (dragonCreate.getCave().getId() == null) {
                dragonCaveEntity = dragonCaveRepository.save(
                        modelMapper.map(dragonCreate.getCave(), DragonCaveEntity.class)
                );
            } else {
                dragonCaveEntity = dragonCaveRepository.getReferenceById(dragonCreate.getCave().getId().longValue());
            }
            dragonEntity.setCave(dragonCaveEntity);
        }

        if (dragonCreate.getKiller().isPresent() && dragonCreate.getKiller().get() != null) {
            PersonEntity personEntity;
            if (dragonCreate.getKiller().get().getId() == null) {
                PersonEntity transientPerson = modelMapper.map(dragonCreate.getKiller().get(), PersonEntity.class);
                if (transientPerson.getLocationEntity() != null && transientPerson.getLocationEntity().getId() == null) {
                    LocationEntity savedLocation = locationRepository.save(transientPerson.getLocationEntity());
                    transientPerson.setLocationEntity(savedLocation);
                }
                personEntity = personRepository.save(transientPerson);
            } else {
                personEntity = personRepository.getReferenceById(dragonCreate.getKiller().get().getId().longValue());
            }
            dragonEntity.setKiller(personEntity);
        }

        if (dragonCreate.getHead().isPresent()) {
            DragonHeadEntity dragonHeadEntity;
            if (dragonCreate.getHead().get().getId() == null) {
                dragonHeadEntity = dragonHeadRepository.save(
                        modelMapper.map(dragonCreate.getHead().get(), DragonHeadEntity.class)
                );
            } else {
                dragonHeadEntity = dragonHeadRepository.getReferenceById(dragonCreate.getHead().get().getId().longValue());
            }
            dragonEntity.setHead(dragonHeadEntity);
        }

        return dragonRepository.save(dragonEntity);
    }

    public boolean existsById(Long id) {
        return dragonRepository.existsById(id);
    }

    public void deleteById(Long id) {
        dragonRepository.deleteById(id);
    }

    public Optional<Dragon> findDragonByAge(Integer age) {
        return dragonRepository.findFirstByAge(age)
                .map(dragonEntity -> modelMapper.map(dragonEntity, Dragon.class));
    }

    public boolean deleteByAge(Integer age) {
        Optional<DragonEntity> dragonEntityOptional = dragonRepository.findFirstByAge(age);

        if (dragonEntityOptional.isPresent()) {
            dragonRepository.delete(dragonEntityOptional.get());
            return true;
        } else {
            return false;
        }
    }

    public int deleteDragonsInCaveWithMaxDepth() {
        return dragonCaveService.getCaveWithMaxDepth()
                .map(cave -> {
                    List<DragonEntity> dragons = dragonRepository.findAllByCaveId(cave.getId());
                    dragonRepository.deleteAll(dragons);
                    return dragons.size();
                })
                .orElse(0);
    }

    public boolean killDragon(Long personId, Long dragonId) {
        Optional<DragonEntity> dragonEntityOptional = dragonRepository.findById(dragonId);
        Optional<PersonEntity> personEntityOptional = personRepository.findById(personId);

        if (dragonEntityOptional.isPresent() && personEntityOptional.isPresent()) {
            DragonEntity dragonEntity = dragonEntityOptional.get();
            dragonEntity.setKiller(personEntityOptional.get());
            dragonRepository.save(dragonEntity);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean reassignAndDelete(Long dragonIdToDelete, Integer newOwnerDragonId) {
        Optional<DragonEntity> dragonToDeleteOpt = dragonRepository.findById(dragonIdToDelete);
        if (dragonToDeleteOpt.isEmpty()) {
            return false;
        }
        DragonEntity dragonToDelete = dragonToDeleteOpt.get();

        Optional<DragonEntity> newOwnerOpt = dragonRepository.findById(newOwnerDragonId.longValue());
        if (newOwnerOpt.isEmpty()) {
            return false;
        }
        DragonEntity newOwner = newOwnerOpt.get();

        if (dragonToDelete.getId().equals(newOwner.getId())) {
            return false;
        }

        if (dragonToDelete.getKiller() != null) {
            newOwner.setKiller(dragonToDelete.getKiller());
        }
        if (dragonToDelete.getCoordinatesEntity() != null) {
            newOwner.setCoordinatesEntity(dragonToDelete.getCoordinatesEntity());
        }
        if (dragonToDelete.getCave() != null) {
            newOwner.setCave(dragonToDelete.getCave());
        }
        if (dragonToDelete.getHead() != null) {
            newOwner.setHead(dragonToDelete.getHead());
        }

        dragonRepository.save(newOwner);

        dragonRepository.delete(dragonToDelete);

        return true;
    }

    public Optional<DragonEntity> update(Dragon dragon) {
        return dragonRepository.findById(dragon.getId().longValue())
                .map(existingDragon -> {
                    existingDragon.setName(dragon.getName());
                    existingDragon.setAge(dragon.getAge());
                    existingDragon.setColor(modelMapper.map(dragon.getColor(), Color.class));
                    existingDragon.setType(modelMapper.map(dragon.getType(), DragonType.class));

                    CoordinatesEntity coordinatesEntity;
                    if (dragon.getCoordinates().getId() == null) {
                        coordinatesEntity = coordinatesRepository.save(
                                modelMapper.map(dragon.getCoordinates(), CoordinatesEntity.class)
                        );
                    } else {
                        coordinatesEntity = coordinatesRepository.getReferenceById(
                                dragon.getCoordinates().getId().longValue()
                        );
                    }
                    existingDragon.setCoordinatesEntity(coordinatesEntity);

                    DragonCaveEntity dragonCaveEntity;
                    if (dragon.getCave().getId() == null) {
                        dragonCaveEntity = dragonCaveRepository.save(
                                modelMapper.map(dragon.getCave(), DragonCaveEntity.class)
                        );
                    } else {
                        dragonCaveEntity = dragonCaveRepository.getReferenceById(
                                dragon.getCave().getId().longValue()
                        );
                    }
                    existingDragon.setCave(dragonCaveEntity);

                    if (dragon.getKiller().isPresent()) {
                        if (dragon.getKiller().get() == null) {
                            existingDragon.setKiller(null);
                        } else {
                            PersonEntity personEntity;
                            if (dragon.getKiller().get().getId() == null) {
                                personEntity = personRepository.save(
                                        modelMapper.map(dragon.getKiller().get(), PersonEntity.class)
                                );
                            } else {
                                personEntity = personRepository.getReferenceById(
                                        dragon.getKiller().get().getId().longValue()
                                );
                            }
                            existingDragon.setKiller(personEntity);
                        }
                    }

                    if (dragon.getCharacter().isPresent()) {
                        existingDragon.setCharacter(
                                dragon.getCharacter().get() != null ?
                                        modelMapper.map(dragon.getCharacter().get(), se.ifmo.models.DragonCharacter.class) :
                                        null
                        );
                    }

                    if (dragon.getHead() != null) {
                        DragonHeadEntity dragonHeadEntity;
                        if (dragon.getHead().getId() == null) {
                            dragonHeadEntity = dragonHeadRepository.save(
                                    modelMapper.map(dragon.getHead(), DragonHeadEntity.class)
                            );
                        } else {
                            dragonHeadEntity = dragonHeadRepository.getReferenceById(
                                    dragon.getHead().getId().longValue()
                            );
                        }
                        existingDragon.setHead(dragonHeadEntity);
                    } else {
                        existingDragon.setHead(null);
                    }

                    return dragonRepository.save(existingDragon);
                });
    }
}
