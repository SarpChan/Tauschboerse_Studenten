package de.hsrm.mi.swtpro.backend.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

/**
 * Entity-Klasse für die Python-Scripts
 * Ein Script hat eine eigene ID, verwaltet die ID vom User, der das Script erstellt/hochlädt,
 * einen fileName, einen fileType, und wird in einem byte-Array gespeichert
 */
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

    @Lob
    @Getter
    @Setter
    private byte[] data;

}