package de.hsrm.mi.swtpro.backend.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Script {

    @Id
    @Getter
    @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter
    @Setter
    @NotNull
    private long userId;

    @Getter
    @Setter
    @NotNull
    private String fileName;

    @Getter
    @Setter
    @NotNull
    private String fileType;

    @Getter
    @Setter
    private String script;

    @Lob
    @Getter
    private byte[] data;

}