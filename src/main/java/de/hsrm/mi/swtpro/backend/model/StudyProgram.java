package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Course of Studies
 */
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
public class StudyProgram {

    @Id
    @Getter @Setter
    @GeneratedValue
    @NotNull
    private long id;

    @Getter @Setter
    @NotEmpty
    private String title;

    @Getter @Setter
    @NotEmpty
    private String degree;

    @Singular("examRegulation")
    @Getter @Setter
    @OneToMany(mappedBy = "studyProgram",cascade= CascadeType.ALL)
    private Set<ExamRegulation> examRegulations;

    @Singular("fieldOfStudy")
    @Getter @Setter
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "FieldOfStudy_StudyProgram",
            joinColumns = { @JoinColumn(name = "studyProgram_id") },
            inverseJoinColumns = { @JoinColumn(name = "field_of_study_id") }
    )
    private Set<FieldOfStudy> fieldsOfStudy;

    /**
     * Adds exam regulation to the collection of exam regulations applicable for this study program 
     * @param examRegulation the examRegulation to add
     */
    public void addExamRegulation(ExamRegulation examRegulation) {
        this.examRegulations.add(examRegulation);
    }

    /**
     * Removes exam regulation from the collection of exam regulations applicable for this study program
     * @param examRegulation the examRegulation to remove
     */
    public void removeExamRegulation(ExamRegulation examRegulation) {
        this.examRegulations.remove(examRegulation);
    }

    /**
     * Empties collection of all exam regulations applicable for this study program
     */
    public void clearExamRegulations() {
        this.examRegulations.clear();
    }

    /**
     * Checks if a given exam regulation applies to given study program
     * @param examRegulation the examRegulation to search 
     * @return true if exam regulation applies to given study program
     */
    public boolean containsExamRegulation(ExamRegulation examRegulation) {
        return this.examRegulations.contains(examRegulation);
    }

}
