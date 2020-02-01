package de.hsrm.mi.swtpro.backend.model;

import lombok.*;

/**
 * The Server sends a serialized AuthenticationResponse to Clients
 */
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private String userRights;
    @Getter
    @Setter
    private long userId;
}
