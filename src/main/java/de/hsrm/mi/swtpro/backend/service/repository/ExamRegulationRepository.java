package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Curriculum;
import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExamRegulationRepository extends JpaRepository<ExamRegulation,Long> {
    public ExamRegulation findById(long id);
    public List<ExamRegulation> findByDate(Date date);
    public List<ExamRegulation> findByRule(int rule);
    public List<ExamRegulation> findByCurricula(Curriculum curriculum);
    public List<ExamRegulation> findByStudyProgram(StudyProgram studyProgram);
}
