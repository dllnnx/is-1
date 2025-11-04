package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import se.ifmo.gen.model.Color;
import se.ifmo.gen.model.Coordinates;
import se.ifmo.gen.model.DragonCave;
import se.ifmo.gen.model.DragonCharacter;
import se.ifmo.gen.model.DragonHead;
import se.ifmo.gen.model.DragonType;
import se.ifmo.gen.model.Person;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Dragon
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class Dragon {

  private Integer id;

  private String name;

  private Coordinates coordinates;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  private DragonCave cave;

  private JsonNullable<Person> killer = JsonNullable.<Person>undefined();

  private Integer age;

  private Color color;

  private DragonType type;

  private JsonNullable<DragonCharacter> character = JsonNullable.<DragonCharacter>undefined();

  private @Nullable DragonHead head;

  public Dragon() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Dragon(Integer id, String name, Coordinates coordinates, OffsetDateTime creationDate, DragonCave cave, Integer age, Color color, DragonType type) {
    this.id = id;
    this.name = name;
    this.coordinates = coordinates;
    this.creationDate = creationDate;
    this.cave = cave;
    this.age = age;
    this.color = color;
    this.type = type;
  }

  public Dragon id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Auto-generated unique ID.   Must not be null and must be greater than 0. 
   * minimum: 1
   * @return id
   */
  @NotNull @Min(1) 
  @Schema(name = "id", description = "Auto-generated unique ID.   Must not be null and must be greater than 0. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Dragon name(String name) {
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

  public Dragon coordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
    return this;
  }

  /**
   * Get coordinates
   * @return coordinates
   */
  @NotNull @Valid 
  @Schema(name = "coordinates", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("coordinates")
  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public Dragon creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Auto-generated creation date.   Must not be null. 
   * @return creationDate
   */
  @NotNull @Valid 
  @Schema(name = "creationDate", description = "Auto-generated creation date.   Must not be null. ", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("creationDate")
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public Dragon cave(DragonCave cave) {
    this.cave = cave;
    return this;
  }

  /**
   * Get cave
   * @return cave
   */
  @NotNull @Valid 
  @Schema(name = "cave", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cave")
  public DragonCave getCave() {
    return cave;
  }

  public void setCave(DragonCave cave) {
    this.cave = cave;
  }

  public Dragon killer(Person killer) {
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
  public JsonNullable<Person> getKiller() {
    return killer;
  }

  public void setKiller(JsonNullable<Person> killer) {
    this.killer = killer;
  }

  public Dragon age(Integer age) {
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

  public Dragon color(Color color) {
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

  public Dragon type(DragonType type) {
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

  public Dragon character(DragonCharacter character) {
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

  public Dragon head(@Nullable DragonHead head) {
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
  public @Nullable DragonHead getHead() {
    return head;
  }

  public void setHead(@Nullable DragonHead head) {
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
    Dragon dragon = (Dragon) o;
    return Objects.equals(this.id, dragon.id) &&
        Objects.equals(this.name, dragon.name) &&
        Objects.equals(this.coordinates, dragon.coordinates) &&
        Objects.equals(this.creationDate, dragon.creationDate) &&
        Objects.equals(this.cave, dragon.cave) &&
        equalsNullable(this.killer, dragon.killer) &&
        Objects.equals(this.age, dragon.age) &&
        Objects.equals(this.color, dragon.color) &&
        Objects.equals(this.type, dragon.type) &&
        equalsNullable(this.character, dragon.character) &&
        Objects.equals(this.head, dragon.head);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, coordinates, creationDate, cave, hashCodeNullable(killer), age, color, type, hashCodeNullable(character), head);
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
    sb.append("class Dragon {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    coordinates: ").append(toIndentedString(coordinates)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
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

