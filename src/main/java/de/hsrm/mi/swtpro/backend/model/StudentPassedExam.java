package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Builder
public class StudentPassedExam {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private float grade;

    @Getter @Setter
    @ManyToOne(mappedBy = "passedExams")
    private Student student;

    @Getter @Setter
    @OneToOne
    private CourseComponent courseComponent;

}