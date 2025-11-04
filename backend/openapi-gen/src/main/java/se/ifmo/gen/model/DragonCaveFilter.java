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
 * DragonCaveFilter
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class DragonCaveFilter {

  private @Nullable Integer id;

  private @Nullable Float depth;

  private @Nullable Long numberOfTreasures;

  public DragonCaveFilter id(@Nullable Integer id) {
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

  public DragonCaveFilter depth(@Nullable Float depth) {
    this.depth = depth;
    return this;
  }

  /**
   * Get depth
   * @return depth
   */
  
  @Schema(name = "depth", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("depth")
  public @Nullable Float getDepth() {
    return depth;
  }

  public void setDepth(@Nullable Float depth) {
    this.depth = depth;
  }

  public DragonCaveFilter numberOfTreasures(@Nullable Long numberOfTreasures) {
    this.numberOfTreasures = numberOfTreasures;
    return this;
  }

  /**
   * Get numberOfTreasures
   * @return numberOfTreasures
   */
  
  @Schema(name = "numberOfTreasures", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberOfTreasures")
  public @Nullable Long getNumberOfTreasures() {
    return numberOfTreasures;
  }

  public void setNumberOfTreasures(@Nullable Long numberOfTreasures) {
    this.numberOfTreasures = numberOfTreasures;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DragonCaveFilter dragonCaveFilter = (DragonCaveFilter) o;
    return Objects.equals(this.id, dragonCaveFilter.id) &&
        Objects.equals(this.depth, dragonCaveFilter.depth) &&
        Objects.equals(this.numberOfTreasures, dragonCaveFilter.numberOfTreasures);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, depth, numberOfTreasures);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DragonCaveFilter {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    depth: ").append(toIndentedString(depth)).append("\n");
    sb.append("    numberOfTreasures: ").append(toIndentedString(numberOfTreasures)).append("\n");
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

