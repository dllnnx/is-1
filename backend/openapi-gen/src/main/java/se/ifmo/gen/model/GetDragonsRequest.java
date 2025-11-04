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
import se.ifmo.gen.model.DragonFilter;
import se.ifmo.gen.model.Pagination;
import se.ifmo.gen.model.Sorting;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GetDragonsRequest
 */

@JsonTypeName("getDragons_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class GetDragonsRequest {

  private @Nullable Pagination pagination;

  @Valid
  private List<@Valid Sorting> sorting = new ArrayList<>();

  private @Nullable DragonFilter dragon;

  public GetDragonsRequest pagination(@Nullable Pagination pagination) {
    this.pagination = pagination;
    return this;
  }

  /**
   * Get pagination
   * @return pagination
   */
  @Valid 
  @Schema(name = "pagination", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pagination")
  public @Nullable Pagination getPagination() {
    return pagination;
  }

  public void setPagination(@Nullable Pagination pagination) {
    this.pagination = pagination;
  }

  public GetDragonsRequest sorting(List<@Valid Sorting> sorting) {
    this.sorting = sorting;
    return this;
  }

  public GetDragonsRequest addSortingItem(Sorting sortingItem) {
    if (this.sorting == null) {
      this.sorting = new ArrayList<>();
    }
    this.sorting.add(sortingItem);
    return this;
  }

  /**
   * Get sorting
   * @return sorting
   */
  @Valid 
  @Schema(name = "sorting", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sorting")
  public List<@Valid Sorting> getSorting() {
    return sorting;
  }

  public void setSorting(List<@Valid Sorting> sorting) {
    this.sorting = sorting;
  }

  public GetDragonsRequest dragon(@Nullable DragonFilter dragon) {
    this.dragon = dragon;
    return this;
  }

  /**
   * Get dragon
   * @return dragon
   */
  @Valid 
  @Schema(name = "dragon", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dragon")
  public @Nullable DragonFilter getDragon() {
    return dragon;
  }

  public void setDragon(@Nullable DragonFilter dragon) {
    this.dragon = dragon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetDragonsRequest getDragonsRequest = (GetDragonsRequest) o;
    return Objects.equals(this.pagination, getDragonsRequest.pagination) &&
        Objects.equals(this.sorting, getDragonsRequest.sorting) &&
        Objects.equals(this.dragon, getDragonsRequest.dragon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pagination, sorting, dragon);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetDragonsRequest {\n");
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
    sb.append("    sorting: ").append(toIndentedString(sorting)).append("\n");
    sb.append("    dragon: ").append(toIndentedString(dragon)).append("\n");
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

