package se.ifmo.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import se.ifmo.gen.api.DragonsApi;
import se.ifmo.gen.model.*;
import se.ifmo.models.DragonEntity;
import se.ifmo.services.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DragonController implements DragonsApi {
    private final DragonService dragonService;
    private final DragonCaveService dragonCaveService;
    private final DragonHeadService dragonHeadService;
    private final CoordinatesService coordinatesService;
    private final LocationService locationService;
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Dragon> getDragonById(Integer id) {
        return dragonService.getDragon(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<GetDragons200Response> getDragons(GetDragonsRequest getDragonsRequest) {
        return ResponseEntity.ok(dragonService.getDragonsWithFilters(getDragonsRequest));
    }

    @Override
    public ResponseEntity<List<DragonCave>> getCaves() {
        return ResponseEntity.ok(dragonCaveService.getAll());
    }

    @Override
    public ResponseEntity<List<DragonHead>> getHeads() {
        return ResponseEntity.ok(dragonHeadService.getAll());
    }

    @Override
    public ResponseEntity<List<Coordinates>> getCoordinates() {
        return ResponseEntity.ok(coordinatesService.getAll());
    }

    @Override
    public ResponseEntity<Integer> getDragonsAgeSum() {
        return ResponseEntity.ok(dragonService.getAgesSum());
    }

    @Override
    public ResponseEntity<List<Location>> getLocations() {
        return ResponseEntity.ok(locationService.getAll());
    }

    @Override
    public ResponseEntity<List<Person>> getPersons() {
        return ResponseEntity.ok(personService.getAll());
    }

    @Override
    public ResponseEntity<Dragon> getMaxTypeDragon() {
        return dragonService.getMaxTypeDragon().map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> createDragon(DragonCreate dragonCreate) {
        DragonEntity saved = dragonService.save(dragonCreate);

        URI location = UriComponentsBuilder
                .fromPath("/dragons/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> updateDragon(Dragon dragon) {
        return dragonService.update(dragon)
                .map(updated -> ResponseEntity.ok().<Void>build())
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> deleteDragonById(Integer id) {
        Long dragonId = id.longValue();

        if (dragonService.existsById(dragonId)) {
            dragonService.deleteById(dragonId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteDragonByAge(Integer age) {
        boolean deleted = dragonService.deleteByAge(age);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteDragonsInCaveWithMaxDepth() {
        int deletedCount = dragonService.deleteDragonsInCaveWithMaxDepth();

        if (deletedCount > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> killDragon(Integer personId, Integer dragonId) {
        boolean success = dragonService.killDragon(personId.longValue(), dragonId.longValue());

        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> reassignAndDeleteDragon(Integer id, ReassignAndDeleteDragonRequest request) {
        boolean success = dragonService.reassignAndDelete(id.longValue(), modelMapper.map(request.getNewOwnerId(), Integer.class));
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
