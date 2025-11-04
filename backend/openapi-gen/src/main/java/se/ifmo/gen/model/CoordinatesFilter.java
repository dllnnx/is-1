package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CoordinatesFilter
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class CoordinatesFilter {

  private @Nullable Integer id;

  private @Nullable Float x;

  private @Nullable Double y;

  public CoordinatesFilter id(@Nullable Integer id) {
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

  public CoordinatesFilter x(@Nullable Float x) {
    this.x = x;
    return this;
  }

  /**
   * Get x
   * @return x
   */
  
  @Schema(name = "x", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("x")
  public @Nullable Float getX() {
    return x;
  }

  public void setX(@Nullable Float x) {
    this.x = x;
  }

  public CoordinatesFilter y(@Nullable Double y) {
    this.y = y;
    return this;
  }

  /**
   * Get y
   * @return y
   */
  
  @Schema(name = "y", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("y")
  public @Nullable Double getY() {
    return y;
  }

  public void setY(@Nullable Double y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CoordinatesFilter coordinatesFilter = (CoordinatesFilter) o;
    return Objects.equals(this.id, coordinatesFilter.id) &&
        Objects.equals(this.x, coordinatesFilter.x) &&
        Objects.equals(this.y, coordinatesFilter.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, x, y);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CoordinatesFilter {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
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

