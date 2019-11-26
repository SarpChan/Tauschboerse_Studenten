package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Course of Studies
 */
@Entity
@AllArgsConstructor
@Builder
public class StudyProgram {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String degree;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "studyProgram")
    private Set<ExamRegulation> examRegulations;

    @Getter @Setter
    @ManyToMany(mappedBy = "studyPrograms")
    private Set<FieldOfStudy> fieldsOfStudy;

    /**
     * Adds exam regulation to the collection of exam regulations applicable for this study program 
     * @param examRegulation
     */
    public void addExamRegulation(ExamRegulation examRegulation) {
        this.examRegulations.add(examRegulation);
    }

    /**
     * Removes exam regulation from the collection of exam regulations applicable for this study program
     * @param examRegulation
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
     * @param examRegulation
     * @return true if exam regulation applies to given study program
     */
    public boolean containsExamRegulation(ExamRegulation examRegulation) {
        return this.examRegulations.contains(examRegulation);
    }

    @Deprecated
    public StudyProgram(int id, String title, String degree) {
        this.id = id;
        this.title = title;
        this.degree = degree;
        this.examRegulations = new HashSet<ExamRegulation>();
    }

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private StudyProgram(Builder builder) {
        this.title = builder.title;
        this.degree = builder.degree;
        this.examRegulations = builder.examRegulations;
        this.fieldsOfStudy = builder.fieldsOfStudy;
    }

    /**
     * Builder class 
     * defines the parameters of the Study Program object to be built
     */
    @Deprecated
    public static class Builder {
        private String title;
        private String degree;
        private Set<ExamRegulation> examRegulations;
        private Set<FieldOfStudy> fieldsOfStudy;

        public Builder(String title, String degree) {
            this.title = title;
            this.degree = degree;
            this.examRegulations = new HashSet<ExamRegulation>();
            this.fieldsOfStudy = new HashSet<FieldOfStudy>();
        }

        public Builder hasExamRegulations(Set<ExamRegulation> examRegulation) {
            this.examRegulations = examRegulation;
            return this;
        }

        public Builder withExamRegulation(ExamRegulation examRegulation) {
            this.examRegulations.add(examRegulation);
            return this;
        }

        public Builder inFieldsOfStudy(Set<FieldOfStudy> fieldOfStudy) {
            this.fieldsOfStudy = fieldOfStudy;
            return this;
        }

        public StudyProgram build() {
            return new StudyProgram(this);
        }
    }
}