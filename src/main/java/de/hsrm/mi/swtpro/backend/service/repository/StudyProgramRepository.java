package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import de.hsrm.mi.swtpro.backend.model.FieldOfStudy;
import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram,Long>{
    List<StudyProgram> findByTitle(String title);
    List<StudyProgram> findByDegree(String degree);
    List<StudyProgram> findByFieldsOfStudy(FieldOfStudy fieldOfStudy);
    List<StudyProgram> findByExamRegulations(ExamRegulation examRegulation);
}
