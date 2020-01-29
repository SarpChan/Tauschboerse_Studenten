package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.annotation.Id;

import lombok.*;

public class Script {
    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotNull
    private long userId;

    @Getter @Setter
    @NotNull
    private String fileName;

    @Getter @Setter
    @NotNull
    private String fileType;

    @Getter @Setter
    private String script;

    @Lob
    @Getter
    private byte[] data;

    public Script(String fileName, String fileType, byte [] data){
        this.fileName = fileName;
        this.fileType = fileType;
        this.data= data;
    }

}