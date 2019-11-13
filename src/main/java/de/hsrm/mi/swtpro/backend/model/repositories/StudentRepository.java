package de.hsrm.mi.swtpro.backend.model.repositories;

import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
@RepositoryRestResource(collectionResourceRel = "springDataRestTestConfigs", path = "springDataRestTestConfigs")

public interface StudentRepository<Student> extends JpaRepository<Student,Integer> {
    public Student findByID(int matrNr);
}
