package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampusRepository extends JpaRepository<Campus,String> {
    public List<Campus> findByName(String name);
    public Campus findByAdress (String adress);
}
