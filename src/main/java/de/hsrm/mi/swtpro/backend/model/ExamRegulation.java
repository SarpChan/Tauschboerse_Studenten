package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Set;

/**
 * Each study program has one or more exam regulations
 * The exam regulation defines the curriculum, cp for each modules
 * and additional rules on examination
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class ExamRegulation {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @DateTimeFormat(pattern ="dd-mm-yyyy")
    @NotNull
    private Date date;

    @Getter @Setter
    private int rule;

    @Getter @Setter
    @ManyToOne
    @NotNull
    private StudyProgram studyProgram;

    @Singular("curriculum")
    @Getter @Setter
    @OneToMany(mappedBy = "examRegulation")
    private Set<Curriculum> curriculums;

    @Singular("student")
    @Getter @Setter
    @OneToMany(mappedBy = "examRegulation")
    private Set<Student> students;

}