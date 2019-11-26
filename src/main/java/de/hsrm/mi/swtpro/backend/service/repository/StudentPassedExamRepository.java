package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentPassedExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPassedExamRepository extends JpaRepository<StudentPassedExam,Long> {
    public List<StudentPassedExam> findByStudent(Student student);
    public List<StudentPassedExam> findByCourseComponent(CourseComponent courseComponent);
    public List<StudentPassedExam> findByPassed(boolean passed);
}
