package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
class StudentAttendsGroup {
    @Id
    @GeneratedValue
    private long id;
    private int priority;
    private boolean participant;

    @OneToOne
    private Student student;

    @OneToOne
    private Group group;


    @Deprecated
    public StudentAttendsGroup(Student student, Group group, int priority, boolean participant) {
        this.student = student;
        this.group = group;
        this.priority = priority;
        this.participant = participant;
    }

    private StudentAttendsGroup(Builder builder) {
        this.student = builder.student;
        this.group = builder.group;
        this.priority = builder.priority;
        this.participant = builder.participant;
    }

    public static class Builder {
        private Student student;
        private Group group;
        private int priority;
        private boolean participant;

        public Builder(Student student, Group group) {
            this.student = student;
            this.group = group;
        }

        public Builder withPriority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder isParticipant(boolean participant) {
            this.participant = participant;
            return this;
        }

        public StudentAttendsGroup build() {
            return new StudentAttendsGroup(this);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isParticipant() {
        return participant;
    }

    public void setParticipant(boolean participant) {
        this.participant = participant;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}