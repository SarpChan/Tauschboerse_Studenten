package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.FieldOfStudy;
import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import de.hsrm.mi.swtpro.backend.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy,Integer> {
    public FieldOfStudy findById(long id);
    public List<FieldOfStudy> findByTitle(String title);
    public List<FieldOfStudy> findByStudyPrograms(StudyProgram studyProgram);
    public List<FieldOfStudy> findByUniversity(University university);
}
