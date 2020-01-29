package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class StudentAttendsCourse {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private Student student;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private Course course;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private Term term;

}