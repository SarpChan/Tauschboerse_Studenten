package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Each study program has one or more exam regulations
 * The exam regulation defines the curriculum, cp for each modules
 * and additional rules on examination
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ExamRegulation {
    @Id
    @GeneratedValue
    private long id;
    private Date date;
    private int rule;

    
    @ManyToOne
    private StudyProgram studyProgram;

    
    @OneToMany(mappedBy = "examRegulation")
    private Set<Curriculum> curricula;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Curriculum> getCurricula() {
        return curricula;
    }

    public void setCurricula(Set<Curriculum> curricula) {
        this.curricula = curricula;
    }
}