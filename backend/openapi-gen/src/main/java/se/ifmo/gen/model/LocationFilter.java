package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.lang.Nullable;
import java.util.NoSuchElementException;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LocationFilter
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class LocationFilter {

  private @Nullable Integer id;

  private @Nullable Double x;

  private @Nullable Integer y;

  private JsonNullable<@Size(max = 416) String> name = JsonNullable.<String>undefined();

  public LocationFilter id(@Nullable Integer id) {
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

  public LocationFilter x(@Nullable Double x) {
    this.x = x;
    return this;
  }

  /**
   * Get x
   * @return x
   */
  
  @Schema(name = "x", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("x")
  public @Nullable Double getX() {
    return x;
  }

  public void setX(@Nullable Double x) {
    this.x = x;
  }

  public LocationFilter y(@Nullable Integer y) {
    this.y = y;
    return this;
  }

  /**
   * Get y
   * @return y
   */
  
  @Schema(name = "y", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("y")
  public @Nullable Integer getY() {
    return y;
  }

  public void setY(@Nullable Integer y) {
    this.y = y;
  }

  public LocationFilter name(String name) {
    this.name = JsonNullable.of(name);
    return this;
  }

  /**
   * Get name
   * @return name
   */
  @Size(max = 416) 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public JsonNullable<@Size(max = 416) String> getName() {
    return name;
  }

  public void setName(JsonNullable<String> name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationFilter locationFilter = (LocationFilter) o;
    return Objects.equals(this.id, locationFilter.id) &&
        Objects.equals(this.x, locationFilter.x) &&
        Objects.equals(this.y, locationFilter.y) &&
        equalsNullable(this.name, locationFilter.name);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, x, y, hashCodeNullable(name));
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
    sb.append("class LocationFilter {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

