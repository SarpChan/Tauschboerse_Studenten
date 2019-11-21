package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * A student is a user
 * Each student has an enrolment number and an email adress
 * as well as a exam regulation and their enrolment term
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student extends User {
    private int enrolementNumber;
    private String mail;


    @OneToOne
    private ExamRegulation examRegulation;


    @OneToOne
    private Term enrolmentTerm;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    private Student(Builder builder) {
        super(builder);
        this.enrolementNumber = builder.enrolementNumber;
        this.enrolmentTerm = builder.enrolmentTerm;
        this.mail = builder.mail;
        this.examRegulation = builder.examRegulation;
    }


     /**
     * Builder class
     * defines the parameters of the student object to be built
     */
    public static class Builder extends User.Builder{

        private ExamRegulation examRegulation;
        private String mail;
        private Term enrolmentTerm;
        private int enrolementNumber;

        public Builder(String firstName, String lastName, String loginName,
        String password, boolean admin, int enrolementNumber, Term enrolmentTerm, String mail, ExamRegulation examRegulation) {
            super(firstName, lastName, loginName, password, admin);
            this.examRegulation = examRegulation;
            this.mail = mail;
            this.enrolmentTerm = enrolmentTerm;
            this.enrolementNumber = enrolementNumber;
        }

        public Student build() {
            return new Student(this);
        }
    }


    public int getEnrolementNumber() {
        return enrolementNumber;
    }

    public void setEnrolementNumber(int enrolementNumber) {
        this.enrolementNumber = enrolementNumber;
    }

    public Term getEnrolementTerm() {
        return enrolmentTerm;
    }

    public void setEnrolementTerm(Term enrolmentTerm) {
        this.enrolmentTerm = enrolmentTerm;
    }

    public ExamRegulation getExamRegulation() {
        return examRegulation;
    }

    public void setExamRegulation(ExamRegulation examRegulation) {
        this.examRegulation = examRegulation;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
}