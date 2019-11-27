package de.hsrm.mi.swtpro.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@Builder
public class StudentAttendsCourse {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    @ManyToOne(mappedBy = "attendsCourses")
    private Student student;

    @Getter @Setter
    @ManyToOne(mappedBy = "studentAttendsCourses")
    private Course course;

    @Getter @Setter
    @ManyToOne(mappedBy = "studentAttendsCourses")
    private Term term;

}