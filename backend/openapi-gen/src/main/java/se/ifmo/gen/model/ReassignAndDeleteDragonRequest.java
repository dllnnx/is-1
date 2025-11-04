package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * ReassignAndDeleteDragonRequest
 */

@JsonTypeName("reassignAndDeleteDragon_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class ReassignAndDeleteDragonRequest {

  private JsonNullable<Integer> newKillerOwnerId = JsonNullable.<Integer>undefined();

  public ReassignAndDeleteDragonRequest newKillerOwnerId(Integer newKillerOwnerId) {
    this.newKillerOwnerId = JsonNullable.of(newKillerOwnerId);
    return this;
  }

  /**
   * ID of the dragon to which the killer should be reassigned, if the killer becomes orphaned.
   * @return newKillerOwnerId
   */
  
  @Schema(name = "newKillerOwnerId", description = "ID of the dragon to which the killer should be reassigned, if the killer becomes orphaned.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("newKillerOwnerId")
  public JsonNullable<Integer> getNewKillerOwnerId() {
    return newKillerOwnerId;
  }

  public void setNewKillerOwnerId(JsonNullable<Integer> newKillerOwnerId) {
    this.newKillerOwnerId = newKillerOwnerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReassignAndDeleteDragonRequest reassignAndDeleteDragonRequest = (ReassignAndDeleteDragonRequest) o;
    return equalsNullable(this.newKillerOwnerId, reassignAndDeleteDragonRequest.newKillerOwnerId);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(newKillerOwnerId));
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
    sb.append("class ReassignAndDeleteDragonRequest {\n");
    sb.append("    newKillerOwnerId: ").append(toIndentedString(newKillerOwnerId)).append("\n");
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

