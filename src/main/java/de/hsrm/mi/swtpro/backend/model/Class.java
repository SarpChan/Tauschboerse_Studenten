package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;

/* Veranstaltung konkret (zb Vorlesung, praktikum) */

@Entity
class Class {
    private String type;
    private int seats;
}