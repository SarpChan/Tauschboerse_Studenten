package de.hsrm.mi.swtpro.backend.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.*;

@AllArgsConstructor
@Builder
public class TimetableModule {
    @Getter @Setter
    private long courseComponentID;
    @Getter @Setter
    private long groupID;
    @Getter @Setter
    private LocalTime startTime;
    @Getter @Setter
    private LocalTime endTime;
    @Getter @Setter
    private DayOfWeek dayOfWeek;
    @Getter @Setter
    private String lecturerName;
    @Getter @Setter
    private String lecturerNameAbbreviation;
    @Getter @Setter
    private String courseTitle;
    @Getter @Setter
    private String courseTitleAbbreviation;
    @Getter @Setter
    private char groupChar;
    @Getter @Setter
    private CourseType courseType;
    @Getter @Setter
    private int roomNumber;
    @Getter @Setter
    private int term;
}

