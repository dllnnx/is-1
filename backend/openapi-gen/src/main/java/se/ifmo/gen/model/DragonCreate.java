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
import se.ifmo.gen.model.DragonCharacter;
import se.ifmo.gen.model.DragonCreateCave;
import se.ifmo.gen.model.DragonCreateCoordinates;
import se.ifmo.gen.model.DragonCreateHead;
import se.ifmo.gen.model.DragonCreateKiller;
import se.ifmo.gen.model.DragonType;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DragonCreate
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class DragonCreate {

  private String name;

  private @Nullable DragonCreateCoordinates coordinates;

  private @Nullable DragonCreateCave cave;

  private JsonNullable<DragonCreateKiller> killer = JsonNullable.<DragonCreateKiller>undefined();

  private Integer age;

  private Color color;

  private DragonType type;

  private JsonNullable<DragonCharacter> character = JsonNullable.<DragonCharacter>undefined();

  private JsonNullable<DragonCreateHead> head = JsonNullable.<DragonCreateHead>undefined();

  public DragonCreate() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public DragonCreate(String name, Integer age, Color color, DragonType type) {
    this.name = name;
    this.age = age;
    this.color = color;
    this.type = type;
  }

  public DragonCreate name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Must not be null or empty.
   * @return name
   */
  @NotNull @Size(min = 1) 
  @Schema(name = "name", description = "Must not be null or empty.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DragonCreate coordinates(@Nullable DragonCreateCoordinates coordinates) {
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
  public @Nullable DragonCreateCoordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(@Nullable DragonCreateCoordinates coordinates) {
    this.coordinates = coordinates;
  }

  public DragonCreate cave(@Nullable DragonCreateCave cave) {
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
  public @Nullable DragonCreateCave getCave() {
    return cave;
  }

  public void setCave(@Nullable DragonCreateCave cave) {
    this.cave = cave;
  }

  public DragonCreate killer(DragonCreateKiller killer) {
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
  public JsonNullable<DragonCreateKiller> getKiller() {
    return killer;
  }

  public void setKiller(JsonNullable<DragonCreateKiller> killer) {
    this.killer = killer;
  }

  public DragonCreate age(Integer age) {
    this.age = age;
    return this;
  }

  /**
   * Must not be null and greater than 0.
   * minimum: 1
   * @return age
   */
  @NotNull @Min(1) 
  @Schema(name = "age", description = "Must not be null and greater than 0.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("age")
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public DragonCreate color(Color color) {
    this.color = color;
    return this;
  }

  /**
   * Get color
   * @return color
   */
  @NotNull @Valid 
  @Schema(name = "color", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("color")
  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public DragonCreate type(DragonType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @NotNull @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public DragonType getType() {
    return type;
  }

  public void setType(DragonType type) {
    this.type = type;
  }

  public DragonCreate character(DragonCharacter character) {
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

  public DragonCreate head(DragonCreateHead head) {
    this.head = JsonNullable.of(head);
    return this;
  }

  /**
   * Get head
   * @return head
   */
  @Valid 
  @Schema(name = "head", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("head")
  public JsonNullable<DragonCreateHead> getHead() {
    return head;
  }

  public void setHead(JsonNullable<DragonCreateHead> head) {
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
    DragonCreate dragonCreate = (DragonCreate) o;
    return Objects.equals(this.name, dragonCreate.name) &&
        Objects.equals(this.coordinates, dragonCreate.coordinates) &&
        Objects.equals(this.cave, dragonCreate.cave) &&
        equalsNullable(this.killer, dragonCreate.killer) &&
        Objects.equals(this.age, dragonCreate.age) &&
        Objects.equals(this.color, dragonCreate.color) &&
        Objects.equals(this.type, dragonCreate.type) &&
        equalsNullable(this.character, dragonCreate.character) &&
        equalsNullable(this.head, dragonCreate.head);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, coordinates, cave, hashCodeNullable(killer), age, color, type, hashCodeNullable(character), hashCodeNullable(head));
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
    sb.append("class DragonCreate {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n");
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

