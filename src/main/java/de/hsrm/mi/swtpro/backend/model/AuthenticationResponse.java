package de.hsrm.mi.swtpro.backend.model;

import lombok.*;

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
