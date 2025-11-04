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
 * DragonHeadFilter
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class DragonHeadFilter {

  private @Nullable Integer id;

  private @Nullable Float toothCount;

  public DragonHeadFilter id(@Nullable Integer id) {
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

  public DragonHeadFilter toothCount(@Nullable Float toothCount) {
    this.toothCount = toothCount;
    return this;
  }

  /**
   * Get toothCount
   * @return toothCount
   */
  
  @Schema(name = "toothCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("toothCount")
  public @Nullable Float getToothCount() {
    return toothCount;
  }

  public void setToothCount(@Nullable Float toothCount) {
    this.toothCount = toothCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DragonHeadFilter dragonHeadFilter = (DragonHeadFilter) o;
    return Objects.equals(this.id, dragonHeadFilter.id) &&
        Objects.equals(this.toothCount, dragonHeadFilter.toothCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, toothCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DragonHeadFilter {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    toothCount: ").append(toIndentedString(toothCount)).append("\n");
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

