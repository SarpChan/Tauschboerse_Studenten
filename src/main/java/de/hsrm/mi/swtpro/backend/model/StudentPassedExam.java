package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @ManyToOne
    private Student student;

    @Getter @Setter
    @OneToOne
    private CourseComponent courseComponent;

}