package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Student extends User {
    @Id
    private int enrolementNumber;
    private Term enrolementTerm;
    private String fieldOfStudy;
    private String examRegulation;
    private String userName;
    private String userPassword;

    public Student(String firstName, String lastName, String fieldOfStudy) {
        super(firstName, lastName);
        this.fieldOfStudy = fieldOfStudy;
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

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getExamRegulation() {
        return examRegulation;
    }

    public void setExamRegulation(String examRegulation) {
        this.examRegulation = examRegulation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
}