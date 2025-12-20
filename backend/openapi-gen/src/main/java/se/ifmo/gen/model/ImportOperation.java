package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import se.ifmo.gen.model.ImportOperationStatus;
import se.ifmo.gen.model.UserRole;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ImportOperation
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class ImportOperation {

  private Integer id;

  private ImportOperationStatus status;

  private UserRole userRole;

  private @Nullable Integer addedCount;

  private @Nullable String errorMessage;

  private @Nullable String fileKey;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  public ImportOperation() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ImportOperation(Integer id, ImportOperationStatus status, UserRole userRole, OffsetDateTime createdAt) {
    this.id = id;
    this.status = status;
    this.userRole = userRole;
    this.createdAt = createdAt;
  }

  public ImportOperation id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Import operation ID
   * @return id
   */
  @NotNull 
  @Schema(name = "id", description = "Import operation ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ImportOperation status(ImportOperationStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @NotNull @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public ImportOperationStatus getStatus() {
    return status;
  }

  public void setStatus(ImportOperationStatus status) {
    this.status = status;
  }

  public ImportOperation userRole(UserRole userRole) {
    this.userRole = userRole;
    return this;
  }

  /**
   * Get userRole
   * @return userRole
   */
  @NotNull @Valid 
  @Schema(name = "userRole", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("userRole")
  public UserRole getUserRole() {
    return userRole;
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }

  public ImportOperation addedCount(@Nullable Integer addedCount) {
    this.addedCount = addedCount;
    return this;
  }

  /**
   * Number of successfully added dragons (only for SUCCESS status)
   * @return addedCount
   */
  
  @Schema(name = "addedCount", description = "Number of successfully added dragons (only for SUCCESS status)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addedCount")
  public @Nullable Integer getAddedCount() {
    return addedCount;
  }

  public void setAddedCount(@Nullable Integer addedCount) {
    this.addedCount = addedCount;
  }

  public ImportOperation errorMessage(@Nullable String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * Error message (only for FAILED status)
   * @return errorMessage
   */
  
  @Schema(name = "errorMessage", description = "Error message (only for FAILED status)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errorMessage")
  public @Nullable String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(@Nullable String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public ImportOperation fileKey(@Nullable String fileKey) {
    this.fileKey = fileKey;
    return this;
  }

  /**
   * Key of the imported file in the storage
   * @return fileKey
   */
  
  @Schema(name = "fileKey", description = "Key of the imported file in the storage", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fileKey")
  public @Nullable String getFileKey() {
    return fileKey;
  }

  public void setFileKey(@Nullable String fileKey) {
    this.fileKey = fileKey;
  }

  public ImportOperation createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * When the import operation was created
   * @return createdAt
   */
  @NotNull @Valid 
  @Schema(name = "createdAt", description = "When the import operation was created", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImportOperation importOperation = (ImportOperation) o;
    return Objects.equals(this.id, importOperation.id) &&
        Objects.equals(this.status, importOperation.status) &&
        Objects.equals(this.userRole, importOperation.userRole) &&
        Objects.equals(this.addedCount, importOperation.addedCount) &&
        Objects.equals(this.errorMessage, importOperation.errorMessage) &&
        Objects.equals(this.fileKey, importOperation.fileKey) &&
        Objects.equals(this.createdAt, importOperation.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, status, userRole, addedCount, errorMessage, fileKey, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImportOperation {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    userRole: ").append(toIndentedString(userRole)).append("\n");
    sb.append("    addedCount: ").append(toIndentedString(addedCount)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    fileKey: ").append(toIndentedString(fileKey)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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

