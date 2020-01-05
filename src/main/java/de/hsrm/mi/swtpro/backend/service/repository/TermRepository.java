package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TermRepository extends JpaRepository<Term,Long> {
    List<Term> findByPeriod(int period);
    List<Term> findByCourses(Course course);
    List<Term> findByStartDate(Date date);
    List<Term> findByEndDate(Date date);
    List<Term> findByGroups(Group group);
    List<Term>findByStudentAttendsCourses(StudentAttendsCourse studentAttendsCourse);
    //Optional<Term> findByEnroledStudents(Student student);


}
