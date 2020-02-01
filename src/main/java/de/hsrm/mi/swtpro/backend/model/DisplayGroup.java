package de.hsrm.mi.swtpro.backend.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

/**
 * A group is part of a course and takes place at a given time and place
 * Each lesson is held by a lecturer in a room, and has a limited capacity of seats
 * There may be multipe lessons for each course, split into different groups
 * A student may only attend one group for each lesson of the same type
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisplayGroup {

    @Id
    @Getter @Setter
    @NotNull
    private long id;

    @Getter @Setter
    @NotNull
    private char groupChar;

    @Getter @Setter
    @NotNull
    private DayOfWeek dayOfWeek;

    @Getter @Setter
    @NotNull
    private LocalTime startTime;

    @Getter @Setter
    @NotNull
    private LocalTime endTime;

    @Getter @Setter
    @NotNull
    private String lecturer;

    @Getter @Setter
    @NotNull
    private int room;

}