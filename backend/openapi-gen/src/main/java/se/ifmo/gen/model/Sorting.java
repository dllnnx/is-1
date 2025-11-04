package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import se.ifmo.gen.model.SortingColumn;
import se.ifmo.gen.model.SortingDirection;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Sorting
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class Sorting {

  private SortingColumn column;

  private SortingDirection direction;

  public Sorting() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Sorting(SortingColumn column, SortingDirection direction) {
    this.column = column;
    this.direction = direction;
  }

  public Sorting column(SortingColumn column) {
    this.column = column;
    return this;
  }

  /**
   * Get column
   * @return column
   */
  @NotNull @Valid 
  @Schema(name = "column", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("column")
  public SortingColumn getColumn() {
    return column;
  }

  public void setColumn(SortingColumn column) {
    this.column = column;
  }

  public Sorting direction(SortingDirection direction) {
    this.direction = direction;
    return this;
  }

  /**
   * Get direction
   * @return direction
   */
  @NotNull @Valid 
  @Schema(name = "direction", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("direction")
  public SortingDirection getDirection() {
    return direction;
  }

  public void setDirection(SortingDirection direction) {
    this.direction = direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sorting sorting = (Sorting) o;
    return Objects.equals(this.column, sorting.column) &&
        Objects.equals(this.direction, sorting.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(column, direction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sorting {\n");
    sb.append("    column: ").append(toIndentedString(column)).append("\n");
    sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
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

