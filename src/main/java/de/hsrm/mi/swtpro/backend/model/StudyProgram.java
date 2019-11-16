package de.hsrm.mi.swtpro.backend.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Course of Studies
 */
@Entity
class StudyProgram {
    private int id;
    private String title;
    private String degree;

    @OneToMany(mappedBy = "studyProgram")
    private Set<ExamRegulation> examRegulations;

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
    private StudyProgram(Builder builder) {
        this.title = builder.title;
        this.degree = builder.degree;
        this.examRegulations = builder.examRegulations;
    }

    /**
     * Builder class 
     * defines the parameters of the Study Program object to be built
     */
    public static class Builder {
        private String title;
        private String degree;
        private Set<ExamRegulation> examRegulations;

        public Builder(String title, String degree) {
            this.title = title;
            this.degree = degree;
            this.examRegulations = new HashSet<ExamRegulation>();
        }

        public Builder hasExamRegulations(Set<ExamRegulation> examRegulation) {
            this.examRegulations = examRegulation;
            return this;
        }

        public Builder withExamRegulation(ExamRegulation examRegulation) {
            this.examRegulations.add(examRegulation);
            return this;
        }

        public StudyProgram build() {
            return new StudyProgram(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Set<ExamRegulation> getExamRegulations() {
        return examRegulations;
    }

    public void setExamRegulations(Set<ExamRegulation> examRegulations) {
        this.examRegulations = examRegulations;
    }

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
}