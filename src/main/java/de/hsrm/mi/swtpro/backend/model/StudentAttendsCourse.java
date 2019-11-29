package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Builder
public class StudentAttendsCourse {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    @ManyToOne
    private Student student;

    @Getter @Setter
    @ManyToOne
    private Course course;

    @Getter @Setter
    @ManyToOne
    private Term term;

}