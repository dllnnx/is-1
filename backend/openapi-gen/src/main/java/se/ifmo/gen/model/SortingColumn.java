package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets SortingColumn
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public enum SortingColumn {
  
  ID("ID"),
  
  NAME("NAME"),
  
  COORDINATES_X("COORDINATES_X"),
  
  COORDINATES_Y("COORDINATES_Y"),
  
  CREATION_DATE("CREATION_DATE"),
  
  CAVE_DEPTH("CAVE_DEPTH"),
  
  CAVE_NUMBER_OF_TREASURES("CAVE_NUMBER_OF_TREASURES"),
  
  PERSON_NAME("PERSON_NAME"),
  
  PERSON_EYE_COLOR("PERSON_EYE_COLOR"),
  
  PERSON_HAIR_COLOR("PERSON_HAIR_COLOR"),
  
  PERSON_LOCATION_X("PERSON_LOCATION_X"),
  
  PERSON_LOCATION_Y("PERSON_LOCATION_Y"),
  
  PERSON_LOCATION_NAME("PERSON_LOCATION_NAME"),
  
  PERSON_HEIGHT("PERSON_HEIGHT"),
  
  PERSON_WEIGHT("PERSON_WEIGHT"),
  
  PERSON_PASSPORT_ID("PERSON_PASSPORT_ID"),
  
  AGE("AGE"),
  
  COLOR("COLOR"),
  
  DRAGON_TYPE("DRAGON_TYPE"),
  
  CHARACTER("CHARACTER"),
  
  HEAD_TOOTH_COUNT("HEAD_TOOTH_COUNT");

  private final String value;

  SortingColumn(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static SortingColumn fromValue(String value) {
    for (SortingColumn b : SortingColumn.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

