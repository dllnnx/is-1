package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import se.ifmo.gen.model.DragonCreate;
import se.ifmo.gen.model.UserRole;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ImportDragonRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class ImportDragonRequest {

  private UserRole role;

  @Valid
  private List<@Valid DragonCreate> dragons = new ArrayList<>();

  public ImportDragonRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ImportDragonRequest(UserRole role, List<@Valid DragonCreate> dragons) {
    this.role = role;
    this.dragons = dragons;
  }

  public ImportDragonRequest role(UserRole role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
   */
  @NotNull @Valid 
  @Schema(name = "role", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("role")
  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public ImportDragonRequest dragons(List<@Valid DragonCreate> dragons) {
    this.dragons = dragons;
    return this;
  }

  public ImportDragonRequest addDragonsItem(DragonCreate dragonsItem) {
    if (this.dragons == null) {
      this.dragons = new ArrayList<>();
    }
    this.dragons.add(dragonsItem);
    return this;
  }

  /**
   * Array of dragons to import
   * @return dragons
   */
  @NotNull @Valid 
  @Schema(name = "dragons", description = "Array of dragons to import", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("dragons")
  public List<@Valid DragonCreate> getDragons() {
    return dragons;
  }

  public void setDragons(List<@Valid DragonCreate> dragons) {
    this.dragons = dragons;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImportDragonRequest importDragonRequest = (ImportDragonRequest) o;
    return Objects.equals(this.role, importDragonRequest.role) &&
        Objects.equals(this.dragons, importDragonRequest.dragons);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, dragons);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImportDragonRequest {\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    dragons: ").append(toIndentedString(dragons)).append("\n");
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

