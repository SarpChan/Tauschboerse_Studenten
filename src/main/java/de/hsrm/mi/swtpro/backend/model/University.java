package de.hsrm.mi.swtpro.backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * A university has a name and the adress of its headquarter
 * It has at least one campus and field of study
 */
@Entity
public class University {
    
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String adress;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "university")
    private List<Campus> campus = new ArrayList<Campus>();


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "university")
    private Set<FieldOfStudy> fieldOfStudies;

    /**
     * Constructor with Builder pattern
     * @param builder
     */
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
    public static class Builder {
        private String name;
        private String adress;
        private List<Campus> campus = new ArrayList<Campus>();
        private Set<FieldOfStudy> fieldOfStudies = new HashSet<>(); 

        public Builder(String name, String adress) {
            this.name = name;
            this.adress = adress;
        }

        public Builder hasCampi(List<Campus> campus) {
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Campus> getCampus() {
        return campus;
    }

    public void setCampus(List<Campus> campus) {
        this.campus = campus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<FieldOfStudy> getFieldOfStudies() {
        return fieldOfStudies;
    }
    
}