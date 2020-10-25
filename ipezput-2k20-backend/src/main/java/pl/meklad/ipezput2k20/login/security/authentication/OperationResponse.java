/**
    This is the common structure for all responses
    if the response contains a list(array) then it will have 'items' field
    if the response contains a single item then it will have 'item'  field
 */


package pl.meklad.ipezput2k20.login.security.authentication;

import lombok.Data;

@Data
public class OperationResponse {
  public enum ResponseStatusEnum {SUCCESS, ERROR, WARNING, NO_ACCESS};
  private ResponseStatusEnum  operationStatus;
  private String  operationMessage;
}
