package pl.meklad.ipezput2k20.login.security.authentication;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Create by dev on 24.10.2020
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SessionResponse extends OperationResponse {
    private SessionItem item;
}
