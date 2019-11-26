package de.hsrm.mi.swtpro.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@AllArgsConstructor
@Builder
class StudentPriorizesGroup {

    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private int priority;

    @Getter @Setter
    @ManyToOne
    private Student student;

    @Getter @Setter
    @ManyToOne
    private Group group;


    @Deprecated
    public StudentPriorizesGroup(Student student, Group group, int priority) {
        this.student = student;
        this.group = group;
        this.priority = priority;
    }

    @Deprecated
    private StudentPriorizesGroup(Builder builder) {
        this.student = builder.student;
        this.group = builder.group;
        this.priority = builder.priority;
    }

    @Deprecated
    public static class Builder {
        private Student student;
        private Group group;
        private int priority;

        public Builder(Student student, Group group) {
            this.student = student;
            this.group = group;
        }

        public Builder withPriority(int priority) {
            this.priority = priority;
            return this;
        }

        public StudentPriorizesGroup build() {
            return new StudentPriorizesGroup(this);
        }
    }
}