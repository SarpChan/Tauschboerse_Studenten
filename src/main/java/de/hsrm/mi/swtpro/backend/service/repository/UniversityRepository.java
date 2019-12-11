package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, String> {
    public List<University> findByAddress(String address);

    public University findByName(String name);

    public Optional<University> findById(long universityID);

    void deleteById(long universityID);
}
