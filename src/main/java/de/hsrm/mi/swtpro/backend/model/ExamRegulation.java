package de.hsrm.mi.swtpro.backend.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

/**
 * Each study program has one or more exam regulations
 * The exam regulation defines the curriculum, cp for each modules
 * and additional rules on examination
 */
@Entity
public
class ExamRegulation implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private Date date;
    private int rule;

    @ManyToOne
    private StudyProgram studyProgram;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    private ExamRegulation(Builder builder) {
        this.date = builder.date;
        this.studyProgram = builder.studyProgram;
        this.rule = builder.rule;
    }

    /**
     * Builder class
     * defines the parameters of the Exam Regulation object to be built
     */
    public static class Builder {
        private Date date;
        private StudyProgram studyProgram;
        private int rule;

        public Builder(Date date) {
            this.date = date;
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

    public long getId() {return id;}


}
