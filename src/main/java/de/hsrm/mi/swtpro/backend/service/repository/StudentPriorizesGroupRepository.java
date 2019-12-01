package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.StudentPriorizesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPriorizesGroupRepository extends JpaRepository<StudentPriorizesGroup, Long> {
    public StudentPriorizesGroup findById(long id);
}
