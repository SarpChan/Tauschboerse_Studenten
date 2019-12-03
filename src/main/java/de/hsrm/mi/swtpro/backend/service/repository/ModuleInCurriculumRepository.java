package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.ModuleInCurriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleInCurriculumRepository extends JpaRepository<ModuleInCurriculum,Long> {
    List<ModuleInCurriculum> findByTermPeriod(int period);
    List<ModuleInCurriculum> findByCurriculum(Curriculum curriculum);
    List<ModuleInCurriculum> findByModule(Module module);
}
