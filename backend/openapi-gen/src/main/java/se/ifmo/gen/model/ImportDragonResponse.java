package se.ifmo.gen.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import se.ifmo.gen.model.ImportOperationStatus;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ImportDragonResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.15.0")
public class ImportDragonResponse {

  private Integer operationId;

  private ImportOperationStatus status;

  private @Nullable Integer addedCount;

  private @Nullable String errorMessage;

  private @Nullable String fileKey;

  public ImportDragonResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ImportDragonResponse(Integer operationId, ImportOperationStatus status) {
    this.operationId = operationId;
    this.status = status;
  }

  public ImportDragonResponse operationId(Integer operationId) {
    this.operationId = operationId;
    return this;
  }

  /**
   * Import operation ID
   * @return operationId
   */
  @NotNull 
  @Schema(name = "operationId", description = "Import operation ID", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("operationId")
  public Integer getOperationId() {
    return operationId;
  }

  public void setOperationId(Integer operationId) {
    this.operationId = operationId;
  }

  public ImportDragonResponse status(ImportOperationStatus status) {
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

  public ImportDragonResponse addedCount(@Nullable Integer addedCount) {
    this.addedCount = addedCount;
    return this;
  }

  /**
   * Number of successfully added dragons
   * @return addedCount
   */
  
  @Schema(name = "addedCount", description = "Number of successfully added dragons", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addedCount")
  public @Nullable Integer getAddedCount() {
    return addedCount;
  }

  public void setAddedCount(@Nullable Integer addedCount) {
    this.addedCount = addedCount;
  }

  public ImportDragonResponse errorMessage(@Nullable String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

  /**
   * Error message if import failed
   * @return errorMessage
   */
  
  @Schema(name = "errorMessage", description = "Error message if import failed", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("errorMessage")
  public @Nullable String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(@Nullable String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public ImportDragonResponse fileKey(@Nullable String fileKey) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImportDragonResponse importDragonResponse = (ImportDragonResponse) o;
    return Objects.equals(this.operationId, importDragonResponse.operationId) &&
        Objects.equals(this.status, importDragonResponse.status) &&
        Objects.equals(this.addedCount, importDragonResponse.addedCount) &&
        Objects.equals(this.errorMessage, importDragonResponse.errorMessage) &&
        Objects.equals(this.fileKey, importDragonResponse.fileKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationId, status, addedCount, errorMessage, fileKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImportDragonResponse {\n");
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    addedCount: ").append(toIndentedString(addedCount)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    fileKey: ").append(toIndentedString(fileKey)).append("\n");
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

