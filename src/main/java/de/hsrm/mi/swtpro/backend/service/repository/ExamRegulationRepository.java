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
    List<ExamRegulation> findByDate(Date date);
    List<ExamRegulation> findByRule(int rule);
    List<ExamRegulation> findByCurriculums(Curriculum curriculum);
    List<ExamRegulation> findByStudyProgram(StudyProgram studyProgram);
}
