package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Student extends User {
    @Id
    private int enrolementNumber;
    private Term enrolementTerm;
    private String mail;
    private ExamRegulation examRegulation;


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