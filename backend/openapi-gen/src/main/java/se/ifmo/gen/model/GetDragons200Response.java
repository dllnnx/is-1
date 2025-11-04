package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import se.ifmo.gen.model.Dragon;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GetDragons200Response
 */

@JsonTypeName("getDragons_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class GetDragons200Response {

  @Valid
  private List<@Valid Dragon> dragons = new ArrayList<>();

  private @Nullable Integer totalPageCount;

  private @Nullable Integer totalDragonsCount;

  public GetDragons200Response dragons(List<@Valid Dragon> dragons) {
    this.dragons = dragons;
    return this;
  }

  public GetDragons200Response addDragonsItem(Dragon dragonsItem) {
    if (this.dragons == null) {
      this.dragons = new ArrayList<>();
    }
    this.dragons.add(dragonsItem);
    return this;
  }

  /**
   * Get dragons
   * @return dragons
   */
  @Valid 
  @Schema(name = "dragons", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dragons")
  public List<@Valid Dragon> getDragons() {
    return dragons;
  }

  public void setDragons(List<@Valid Dragon> dragons) {
    this.dragons = dragons;
  }

  public GetDragons200Response totalPageCount(@Nullable Integer totalPageCount) {
    this.totalPageCount = totalPageCount;
    return this;
  }

  /**
   * Get totalPageCount
   * @return totalPageCount
   */
  
  @Schema(name = "total_page_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total_page_count")
  public @Nullable Integer getTotalPageCount() {
    return totalPageCount;
  }

  public void setTotalPageCount(@Nullable Integer totalPageCount) {
    this.totalPageCount = totalPageCount;
  }

  public GetDragons200Response totalDragonsCount(@Nullable Integer totalDragonsCount) {
    this.totalDragonsCount = totalDragonsCount;
    return this;
  }

  /**
   * Get totalDragonsCount
   * @return totalDragonsCount
   */
  
  @Schema(name = "total_dragons_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total_dragons_count")
  public @Nullable Integer getTotalDragonsCount() {
    return totalDragonsCount;
  }

  public void setTotalDragonsCount(@Nullable Integer totalDragonsCount) {
    this.totalDragonsCount = totalDragonsCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetDragons200Response getDragons200Response = (GetDragons200Response) o;
    return Objects.equals(this.dragons, getDragons200Response.dragons) &&
        Objects.equals(this.totalPageCount, getDragons200Response.totalPageCount) &&
        Objects.equals(this.totalDragonsCount, getDragons200Response.totalDragonsCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dragons, totalPageCount, totalDragonsCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetDragons200Response {\n");
    sb.append("    dragons: ").append(toIndentedString(dragons)).append("\n");
    sb.append("    totalPageCount: ").append(toIndentedString(totalPageCount)).append("\n");
    sb.append("    totalDragonsCount: ").append(toIndentedString(totalDragonsCount)).append("\n");
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

