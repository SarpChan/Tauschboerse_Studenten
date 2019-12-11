package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.ModuleInCurriculum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {
    List<Curriculum>findByTermPeriod(int period);
    List<Curriculum>findByModulesInCurriculum(ModuleInCurriculum module);
    List<Curriculum>findByExamRegulation(ExamRegulation examRegulation);
}
