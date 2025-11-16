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
import se.ifmo.gen.model.Location;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * DragonCreatePerson
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class DragonCreatePerson {

  private @Nullable Integer id;

  private @Nullable String name;

  private @Nullable Color eyeColor;

  private JsonNullable<Color> hairColor = JsonNullable.<Color>undefined();

  private JsonNullable<Location> location = JsonNullable.<Location>undefined();

  private JsonNullable<@DecimalMin("0.0000010") Double> height = JsonNullable.<Double>undefined();

  private @Nullable Double weight;

  private @Nullable String passportID;

  public DragonCreatePerson id(@Nullable Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Auto-generated unique ID.   Must not be null and must be greater than 0. 
   * minimum: 1
   * @return id
   */
  @Min(1) 
  @Schema(name = "id", description = "Auto-generated unique ID.   Must not be null and must be greater than 0. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable Integer getId() {
    return id;
  }

  public void setId(@Nullable Integer id) {
    this.id = id;
  }

  public DragonCreatePerson name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @Size(min = 1) 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public DragonCreatePerson eyeColor(@Nullable Color eyeColor) {
    this.eyeColor = eyeColor;
    return this;
  }

  /**
   * Get eyeColor
   * @return eyeColor
   */
  @Valid 
  @Schema(name = "eyeColor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("eyeColor")
  public @Nullable Color getEyeColor() {
    return eyeColor;
  }

  public void setEyeColor(@Nullable Color eyeColor) {
    this.eyeColor = eyeColor;
  }

  public DragonCreatePerson hairColor(Color hairColor) {
    this.hairColor = JsonNullable.of(hairColor);
    return this;
  }

  /**
   * Get hairColor
   * @return hairColor
   */
  @Valid 
  @Schema(name = "hairColor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hairColor")
  public JsonNullable<Color> getHairColor() {
    return hairColor;
  }

  public void setHairColor(JsonNullable<Color> hairColor) {
    this.hairColor = hairColor;
  }

  public DragonCreatePerson location(Location location) {
    this.location = JsonNullable.of(location);
    return this;
  }

  /**
   * Get location
   * @return location
   */
  @Valid 
  @Schema(name = "location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("location")
  public JsonNullable<Location> getLocation() {
    return location;
  }

  public void setLocation(JsonNullable<Location> location) {
    this.location = location;
  }

  public DragonCreatePerson height(Double height) {
    this.height = JsonNullable.of(height);
    return this;
  }

  /**
   * Must be greater than 0 if present. 
   * minimum: 0.0000010
   * @return height
   */
  @DecimalMin("0.0000010") 
  @Schema(name = "height", description = "Must be greater than 0 if present. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("height")
  public JsonNullable<@DecimalMin("0.0000010") Double> getHeight() {
    return height;
  }

  public void setHeight(JsonNullable<Double> height) {
    this.height = height;
  }

  public DragonCreatePerson weight(@Nullable Double weight) {
    this.weight = weight;
    return this;
  }

  /**
   * Get weight
   * minimum: 0.0000010
   * @return weight
   */
  @DecimalMin("0.0000010") 
  @Schema(name = "weight", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("weight")
  public @Nullable Double getWeight() {
    return weight;
  }

  public void setWeight(@Nullable Double weight) {
    this.weight = weight;
  }

  public DragonCreatePerson passportID(@Nullable String passportID) {
    this.passportID = passportID;
    return this;
  }

  /**
   * Unique passport identifier. Must not be null. 
   * @return passportID
   */
  
  @Schema(name = "passportID", description = "Unique passport identifier. Must not be null. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("passportID")
  public @Nullable String getPassportID() {
    return passportID;
  }

  public void setPassportID(@Nullable String passportID) {
    this.passportID = passportID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DragonCreatePerson dragonCreatePerson = (DragonCreatePerson) o;
    return Objects.equals(this.id, dragonCreatePerson.id) &&
        Objects.equals(this.name, dragonCreatePerson.name) &&
        Objects.equals(this.eyeColor, dragonCreatePerson.eyeColor) &&
        equalsNullable(this.hairColor, dragonCreatePerson.hairColor) &&
        equalsNullable(this.location, dragonCreatePerson.location) &&
        equalsNullable(this.height, dragonCreatePerson.height) &&
        Objects.equals(this.weight, dragonCreatePerson.weight) &&
        Objects.equals(this.passportID, dragonCreatePerson.passportID);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, eyeColor, hashCodeNullable(hairColor), hashCodeNullable(location), hashCodeNullable(height), weight, passportID);
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
    sb.append("class DragonCreatePerson {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    eyeColor: ").append(toIndentedString(eyeColor)).append("\n");
    sb.append("    hairColor: ").append(toIndentedString(hairColor)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
    sb.append("    passportID: ").append(toIndentedString(passportID)).append("\n");
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

