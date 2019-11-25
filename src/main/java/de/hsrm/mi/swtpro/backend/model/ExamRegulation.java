package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Each study program has one or more exam regulations
 * The exam regulation defines the curriculum, cp for each modules
 * and additional rules on examination
 */
@Entity
@AllArgsConstructor
@Builder
public class ExamRegulation {
    
    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private Date date;

    @Getter @Setter
    private int rule;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    private StudyProgram studyProgram;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "examRegulation")
    private Set<Curriculum> curricula;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private ExamRegulation(Builder builder) {
        this.date = builder.date;
        this.studyProgram = builder.studyProgram;
        this.rule = builder.rule;
        this.curricula = builder.curricula;
    }

    /**
     * Builder class 
     * defines the parameters of the Exam Regulation object to be built
     */
    @Deprecated
    public static class Builder {
        private Date date;
        private StudyProgram studyProgram;
        private int rule;
        private Set<Curriculum> curricula;
    
        public Builder(Date date) {
            this.date = date;
            this.curricula = new HashSet<Curriculum>();
        }

        public Builder forStudyProgram(StudyProgram studyProgram) {
            this.studyProgram = studyProgram;
            return this;
        }

        public Builder hasRule(int rule) {
            this.rule = rule;
            return this;
        }

        public ExamRegulation build() {
            return new ExamRegulation(this);
        }
    }
}