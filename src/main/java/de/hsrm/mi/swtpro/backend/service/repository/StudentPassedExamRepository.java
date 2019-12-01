package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.StudentPassedExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPassedExamRepository extends JpaRepository<StudentPassedExam, Long> {
    public StudentPassedExam findById(long id);
}
