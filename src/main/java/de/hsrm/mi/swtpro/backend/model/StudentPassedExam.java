package de.hsrm.mi.swtpro.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentPassedExam {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private float grade;

    @Getter @Setter
    @ManyToOne
    private Student student;

    @Getter @Setter
    @OneToOne(mappedBy = "studentPassedExam")
    private CourseComponent courseComponent;

}