package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByTitle(String name);
    List<Course> findByCourseComponents(CourseComponent courseComponent);
    List<Course> findByModules(Module module);
    List<Course> findByOwner(User user);
    List<Course> findByTerms(Term term);
    List<Course> findByStudentAttendsCourses(StudentAttendsCourse studentAttendsCourse);

}
