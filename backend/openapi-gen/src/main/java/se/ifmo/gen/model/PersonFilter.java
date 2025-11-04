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
import se.ifmo.gen.model.LocationFilter;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PersonFilter
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class PersonFilter {

  private @Nullable Integer id;

  private @Nullable String name;

  private @Nullable Color eyeColor;

  private JsonNullable<Color> hairColor = JsonNullable.<Color>undefined();

  private JsonNullable<LocationFilter> location = JsonNullable.<LocationFilter>undefined();

  private JsonNullable<Double> height = JsonNullable.<Double>undefined();

  private @Nullable Double weight;

  private @Nullable String passportID;

  public PersonFilter id(@Nullable Integer id) {
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

  public PersonFilter name(@Nullable String name) {
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

  public PersonFilter eyeColor(@Nullable Color eyeColor) {
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

  public PersonFilter hairColor(Color hairColor) {
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

  public PersonFilter location(LocationFilter location) {
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
  public JsonNullable<LocationFilter> getLocation() {
    return location;
  }

  public void setLocation(JsonNullable<LocationFilter> location) {
    this.location = location;
  }

  public PersonFilter height(Double height) {
    this.height = JsonNullable.of(height);
    return this;
  }

  /**
   * Must be greater than 0 if present. 
   * @return height
   */
  
  @Schema(name = "height", description = "Must be greater than 0 if present. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("height")
  public JsonNullable<Double> getHeight() {
    return height;
  }

  public void setHeight(JsonNullable<Double> height) {
    this.height = height;
  }

  public PersonFilter weight(@Nullable Double weight) {
    this.weight = weight;
    return this;
  }

  /**
   * Get weight
   * @return weight
   */
  
  @Schema(name = "weight", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("weight")
  public @Nullable Double getWeight() {
    return weight;
  }

  public void setWeight(@Nullable Double weight) {
    this.weight = weight;
  }

  public PersonFilter passportID(@Nullable String passportID) {
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
    PersonFilter personFilter = (PersonFilter) o;
    return Objects.equals(this.id, personFilter.id) &&
        Objects.equals(this.name, personFilter.name) &&
        Objects.equals(this.eyeColor, personFilter.eyeColor) &&
        equalsNullable(this.hairColor, personFilter.hairColor) &&
        equalsNullable(this.location, personFilter.location) &&
        equalsNullable(this.height, personFilter.height) &&
        Objects.equals(this.weight, personFilter.weight) &&
        Objects.equals(this.passportID, personFilter.passportID);
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
    sb.append("class PersonFilter {\n");
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

