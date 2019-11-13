package de.hsrm.mi.swtpro.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class University {

    @Id
    private String name;
    private String adress;
    @OneToMany(mappedBy = "university")
    private List<Campus> campus = new ArrayList<Campus>();    


    public University(String name, String adress, List<Campus> campus) {
        this.name = name;
        this.adress = adress;
        this.campus = campus;
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
    
}