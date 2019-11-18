package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student extends User {
    private int enrolementNumber;
    private String mail;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne
    private ExamRegulation examRegulation;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne
    private Term enrolementTerm;


    public Student(String firstName, String lastName, String loginName, String password, boolean admin, 
        int enrolementNumber, Term enrolmentTerm, String mail, ExamRegulation examRegulation) {
        super(firstName, lastName, loginName, password, admin);
        this.enrolementNumber = enrolementNumber;
        this.enrolementTerm = enrolmentTerm;
        this.mail = mail;
        this.examRegulation = examRegulation;
    }


    public int getEnrolementNumber() {
        return enrolementNumber;
    }

    public void setEnrolementNumber(int enrolementNumber) {
        this.enrolementNumber = enrolementNumber;
    }

    public Term getEnrolementTerm() {
        return enrolementTerm;
    }

    public void setEnrolementTerm(Term enrolementTerm) {
        this.enrolementTerm = enrolementTerm;
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