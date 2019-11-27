package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum,Long> {
    public Curriculum findById(long id);
    public List<Curriculum>findByTerm(int period);
    public List<Curriculum>findByModules(Module module);
    public List<Curriculum>findByExamRegulation(ExamRegulation examRegulation);
}
