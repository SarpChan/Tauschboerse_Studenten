package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.ModuleInCurriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleInCurriculumRepository extends JpaRepository<ModuleInCurriculum, Long> {
    public ModuleInCurriculum findById(long id);
}
