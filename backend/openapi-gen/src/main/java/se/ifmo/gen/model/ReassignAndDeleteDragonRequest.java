package se.ifmo.gen.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * ReassignAndDeleteDragonRequest
 */

@JsonTypeName("reassignAndDeleteDragon_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class ReassignAndDeleteDragonRequest {

  private Integer newOwnerId;

  public ReassignAndDeleteDragonRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ReassignAndDeleteDragonRequest(Integer newOwnerId) {
    this.newOwnerId = newOwnerId;
  }

  public ReassignAndDeleteDragonRequest newOwnerId(Integer newOwnerId) {
    this.newOwnerId = newOwnerId;
    return this;
  }

  /**
   * ID of the dragon to which all dependencies (killer, cave, coordinates, head) will be reassigned.
   * @return newOwnerId
   */
  @NotNull
  @Schema(name = "newOwnerId", description = "ID of the dragon to which all dependencies (killer, cave, coordinates, head) will be reassigned.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("newOwnerId")
  public Integer getNewOwnerId() {
    return newOwnerId;
  }

  public void setNewOwnerId(Integer newOwnerId) {
    this.newOwnerId = newOwnerId;
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
    return Objects.equals(this.newOwnerId, reassignAndDeleteDragonRequest.newOwnerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(newOwnerId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReassignAndDeleteDragonRequest {\n");
    sb.append("    newOwnerId: ").append(toIndentedString(newOwnerId)).append("\n");
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
