package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByEnrolementNumber(int enrolementNumber);
    Optional<Student> findByUser(User user);
    List<Student> findByMail(String mail);
    List<Student> findByEnrolmentTerm(Term term);
    List<Student> findByGroups(Group group);
    List<Student> findByPrioritizeGroups(StudentPrioritizesGroup group);
    List<Student> findByPassedExams(StudentPassedExam courseComponent);
    List<Student> findByExamRegulation(ExamRegulation examRegulation);
    List<Student> findByAttendCourses(StudentAttendsCourse studentAttendsCourse);
}
