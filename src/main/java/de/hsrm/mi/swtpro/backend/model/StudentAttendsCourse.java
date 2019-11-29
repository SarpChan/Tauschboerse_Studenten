package de.hsrm.mi.swtpro.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
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