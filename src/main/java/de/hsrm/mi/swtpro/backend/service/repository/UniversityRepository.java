package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Campus;
import de.hsrm.mi.swtpro.backend.model.FieldOfStudy;
import de.hsrm.mi.swtpro.backend.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University,Long> {
    List<University> findByAddress(String address);
    List<University> findByFieldsOfStudy(FieldOfStudy fieldOfStudy);
    List<University> findByCampuses(Campus campus);
    Optional<University> findByName(String name);

}
