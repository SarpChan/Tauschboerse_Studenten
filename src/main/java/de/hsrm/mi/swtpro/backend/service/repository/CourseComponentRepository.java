package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseComponentRepository extends JpaRepository<CourseComponent,Long> {
    List<CourseComponent> findByType(CourseType type );
    List<CourseComponent> findByCreditPoints(int creditPoints);
    List<CourseComponent> findByExam(String exam);
    List<CourseComponent> findByGroups(Group group);
    List<CourseComponent> findByCourse(Course course);
    List<CourseComponent> findByStudentPassedExam(StudentPassedExam studentPassedExam);
}
