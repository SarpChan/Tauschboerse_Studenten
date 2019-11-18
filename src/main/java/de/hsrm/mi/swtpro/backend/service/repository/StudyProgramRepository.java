package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram,Long>{
    public List<StudyProgram> findByTitle(String title);
    public List<StudyProgram> findByDegree(String degree);
}
