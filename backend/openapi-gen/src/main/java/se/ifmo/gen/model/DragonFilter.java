package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.lang.Nullable;
import se.ifmo.gen.model.Color;
import se.ifmo.gen.model.CoordinatesFilter;
import se.ifmo.gen.model.DateRangeFilter;
import se.ifmo.gen.model.DragonCaveFilter;
import se.ifmo.gen.model.DragonCharacter;
import se.ifmo.gen.model.DragonHeadFilter;
import se.ifmo.gen.model.DragonType;
import se.ifmo.gen.model.PersonFilter;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DragonFilter
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class DragonFilter {

  private @Nullable Integer id;

  private @Nullable String name;

  private @Nullable CoordinatesFilter coordinates;

  private @Nullable DateRangeFilter creationDateRange;

  private @Nullable DragonCaveFilter cave;

  private JsonNullable<PersonFilter> killer = JsonNullable.<PersonFilter>undefined();

  private @Nullable Integer age;

  private @Nullable Color color;

  private @Nullable DragonType type;

  private JsonNullable<DragonCharacter> character = JsonNullable.<DragonCharacter>undefined();

  private @Nullable DragonHeadFilter head;

  public DragonFilter id(@Nullable Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Auto-generated unique ID.   Must not be null and must be greater than 0. 
   * @return id
   */
  
  @Schema(name = "id", description = "Auto-generated unique ID.   Must not be null and must be greater than 0. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable Integer getId() {
    return id;
  }

  public void setId(@Nullable Integer id) {
    this.id = id;
  }

  public DragonFilter name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Must not be null or empty.
   * @return name
   */
  @Size(min = 1) 
  @Schema(name = "name", description = "Must not be null or empty.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public DragonFilter coordinates(@Nullable CoordinatesFilter coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  /**
   * Get coordinates
   * @return coordinates
   */
  @Valid 
  @Schema(name = "coordinates", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("coordinates")
  public @Nullable CoordinatesFilter getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(@Nullable CoordinatesFilter coordinates) {
    this.coordinates = coordinates;
  }

  public DragonFilter creationDateRange(@Nullable DateRangeFilter creationDateRange) {
    this.creationDateRange = creationDateRange;
    return this;
  }

  /**
   * Get creationDateRange
   * @return creationDateRange
   */
  @Valid 
  @Schema(name = "creationDateRange", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("creationDateRange")
  public @Nullable DateRangeFilter getCreationDateRange() {
    return creationDateRange;
  }

  public void setCreationDateRange(@Nullable DateRangeFilter creationDateRange) {
    this.creationDateRange = creationDateRange;
  }

  public DragonFilter cave(@Nullable DragonCaveFilter cave) {
    this.cave = cave;
    return this;
  }

  /**
   * Get cave
   * @return cave
   */
  @Valid 
  @Schema(name = "cave", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cave")
  public @Nullable DragonCaveFilter getCave() {
    return cave;
  }

  public void setCave(@Nullable DragonCaveFilter cave) {
    this.cave = cave;
  }

  public DragonFilter killer(PersonFilter killer) {
    this.killer = JsonNullable.of(killer);
    return this;
  }

  /**
   * Get killer
   * @return killer
   */
  @Valid 
  @Schema(name = "killer", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("killer")
  public JsonNullable<PersonFilter> getKiller() {
    return killer;
  }

  public void setKiller(JsonNullable<PersonFilter> killer) {
    this.killer = killer;
  }

  public DragonFilter age(@Nullable Integer age) {
    this.age = age;
    return this;
  }

  /**
   * Must not be null and greater than 0.
   * @return age
   */
  
  @Schema(name = "age", description = "Must not be null and greater than 0.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("age")
  public @Nullable Integer getAge() {
    return age;
  }

  public void setAge(@Nullable Integer age) {
    this.age = age;
  }

  public DragonFilter color(@Nullable Color color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
   */
  @Valid 
  @Schema(name = "color", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("color")
  public @Nullable Color getColor() {
    return color;
  }

  public void setColor(@Nullable Color color) {
    this.color = color;
  }

  public DragonFilter type(@Nullable DragonType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public @Nullable DragonType getType() {
    return type;
  }

  public void setType(@Nullable DragonType type) {
    this.type = type;
  }

  public DragonFilter character(DragonCharacter character) {
    this.character = JsonNullable.of(character);
    return this;
  }

  /**
   * Get character
   * @return character
   */
  @Valid 
  @Schema(name = "character", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("character")
  public JsonNullable<DragonCharacter> getCharacter() {
    return character;
  }

  public void setCharacter(JsonNullable<DragonCharacter> character) {
    this.character = character;
  }

  public DragonFilter head(@Nullable DragonHeadFilter head) {
    this.head = head;
    return this;
  }

  /**
   * Get head
   * @return head
   */
  @Valid 
  @Schema(name = "head", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("head")
  public @Nullable DragonHeadFilter getHead() {
    return head;
  }

  public void setHead(@Nullable DragonHeadFilter head) {
    this.head = head;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DragonFilter dragonFilter = (DragonFilter) o;
    return Objects.equals(this.id, dragonFilter.id) &&
        Objects.equals(this.name, dragonFilter.name) &&
        Objects.equals(this.coordinates, dragonFilter.coordinates) &&
        Objects.equals(this.creationDateRange, dragonFilter.creationDateRange) &&
        Objects.equals(this.cave, dragonFilter.cave) &&
        equalsNullable(this.killer, dragonFilter.killer) &&
        Objects.equals(this.age, dragonFilter.age) &&
        Objects.equals(this.color, dragonFilter.color) &&
        Objects.equals(this.type, dragonFilter.type) &&
        equalsNullable(this.character, dragonFilter.character) &&
        Objects.equals(this.head, dragonFilter.head);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, coordinates, creationDateRange, cave, hashCodeNullable(killer), age, color, type, hashCodeNullable(character), head);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DragonFilter {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n");
    sb.append("    creationDateRange: ").append(toIndentedString(creationDateRange)).append("\n");
    sb.append("    cave: ").append(toIndentedString(cave)).append("\n");
    sb.append("    killer: ").append(toIndentedString(killer)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    character: ").append(toIndentedString(character)).append("\n");
    sb.append("    head: ").append(toIndentedString(head)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

