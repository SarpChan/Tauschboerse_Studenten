package de.hsrm.mi.swtpro.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
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
