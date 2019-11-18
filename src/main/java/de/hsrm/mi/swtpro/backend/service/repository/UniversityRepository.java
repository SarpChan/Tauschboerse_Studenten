package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University,String> {
    public List<University> findByAdress(String address);
    public University findByName(String name);

}
