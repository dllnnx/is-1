package se.ifmo.controllers;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import se.ifmo.gen.api.DragonsApi;
import se.ifmo.gen.model.*;
import se.ifmo.models.DragonEntity;
import se.ifmo.services.*;

@RestController
@RequiredArgsConstructor
public class DragonController implements DragonsApi {
  private final DragonService dragonService;
  private final DragonCaveService dragonCaveService;
  private final DragonHeadService dragonHeadService;
  private final CoordinatesService coordinatesService;
  private final LocationService locationService;
  private final PersonService personService;
  private final ImportService importService;
  private final ModelMapper modelMapper;

  @Override
  public ResponseEntity<Dragon> getDragonById(Integer id) {
    return dragonService
        .getDragon(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
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
    return dragonService
        .getMaxTypeDragon()
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<Void> createDragon(DragonCreate dragonCreate) {
    DragonEntity saved = dragonService.save(dragonCreate);

    URI location =
        UriComponentsBuilder.fromPath("/dragons/{id}").buildAndExpand(saved.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @Override
  public ResponseEntity<Void> updateDragon(Dragon dragon) {
    return dragonService
        .update(dragon)
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
  public ResponseEntity<Void> reassignAndDeleteDragon(
      Integer id, ReassignAndDeleteDragonRequest request) {
    boolean success =
        dragonService.reassignAndDelete(
            id.longValue(), modelMapper.map(request.getNewOwnerId(), Integer.class));
    if (success) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<ImportDragonResponse> importDragons(UserRole role, MultipartFile file) {
    se.ifmo.models.UserRole userRole = role == UserRole.ADMIN
        ? se.ifmo.models.UserRole.ADMIN
        : se.ifmo.models.UserRole.USER;

    ImportDragonResponse response = importService.importDragonsFromFile(file, userRole);
    return ResponseEntity.ok(response);
  }

  @Override
  public ResponseEntity<List<ImportOperation>> getImportHistory(UserRole role) {
    se.ifmo.models.UserRole userRole = role == UserRole.ADMIN
        ? se.ifmo.models.UserRole.ADMIN
        : se.ifmo.models.UserRole.USER;

    List<ImportOperation> history = importService.getImportHistory(userRole);
    return ResponseEntity.ok(history);
  }

  @Override
  public ResponseEntity<Resource> downloadImportFile(Integer operationId, UserRole role) {
    try {
      se.ifmo.models.UserRole userRole = role == UserRole.ADMIN
          ? se.ifmo.models.UserRole.ADMIN
          : se.ifmo.models.UserRole.USER;
          
      List<ImportOperation> history = importService.getImportHistory(userRole);
      
      ImportOperation operation = history.stream()
          .filter(op -> op.getId().equals(operationId))
          .findFirst()
          .orElse(null);
          
      if (operation == null || operation.getFileKey() == null) {
        return ResponseEntity.notFound().build();
      }
      
      InputStream fileStream = importService.getFile(operation.getFileKey());
      InputStreamResource resource = new InputStreamResource(fileStream);
      
      return ResponseEntity.ok()
          .contentType(MediaType.APPLICATION_OCTET_STREAM)
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"import_" + operationId + ".json\"")
          .body(resource);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    } finally {
        if (fileStream != null) {
            try {
                fileStream.close();
            } catch (Exception _) {
            }
        }
    }
  }
}
