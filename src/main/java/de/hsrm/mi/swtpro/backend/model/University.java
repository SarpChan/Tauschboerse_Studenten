package de.hsrm.mi.swtpro.backend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * A university has a name and the adress of its headquarter
 * It has at least one campus and field of study
 */
@Entity
@AllArgsConstructor
@Builder
public class University {
    
    @Id
    @Getter @Setter
    @GeneratedValue
    private long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String adress;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "university")
    private Set<Campus> campus;

    @Getter @Setter
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "university")
    private Set<FieldOfStudy> fieldOfStudies;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
    @Deprecated
    private University(Builder builder) {
        this.name = builder.name;
        this.adress = builder.adress;
        this.fieldOfStudies = builder.fieldOfStudies;
        this.campus = builder.campus;
    }

    /**
     * Builder class 
     * defines the parameters of the university object to be built
     */
    @Deprecated
    public static class Builder {
        private String name;
        private String adress;
        private Set<Campus> campus = new HashSet<Campus>();
        private Set<FieldOfStudy> fieldOfStudies = new HashSet<>(); 

        public Builder(String name, String adress) {
            this.name = name;
            this.adress = adress;
        }

        public Builder hasCampi(Set<Campus> campus) {
            this.campus = campus;
            return this;
        }

        public Builder hasCampus(Campus campus) {
            this.campus.add(campus);
            return this;
        }

        public Builder hasFieldsOfStudies(Set<FieldOfStudy> fieldOfStudies) {
            this.fieldOfStudies = fieldOfStudies;
            return this;
        }

        public Builder hasFieldOfStudy(FieldOfStudy fieldOfStudies) {
            this.fieldOfStudies.add(fieldOfStudies);
            return this;
        }

        public University build() {
            return new University(this);
        }
    }
}