package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentAttendsCourse;
import de.hsrm.mi.swtpro.backend.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAttendsCourseRepository extends JpaRepository<StudentAttendsCourse,Long> {
    public List<StudentAttendsCourse> findByCourse(Course course);
    public List<StudentAttendsCourse> findByTerm(Term term);
    public List<StudentAttendsCourse> findByStudent(Student student);
}
