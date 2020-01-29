package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    List<Group> findByGroupChar(char c);
    List<Group> findByTerm(Term term);
    List<Group> findByLecturer(Lecturer lecturer);
    List<Group> findByCourseComponent(CourseComponent courseComponent);
    List<Group> findByStudents(Student student);
    List<Group> findByPrioritizeGroups(StudentPrioritizesGroup student);
    List<Group> findByRoom(Room room);
    List<Group> findByDayOfWeek(DayOfWeek dayOfWeek);
    List<Group> findByEndTime(LocalTime time);
    List<Group> findByStartTime(LocalTime time);
    List<Group> findBySlots(int slots);
    List<Group> findByCourseComponentAndTerm(CourseComponent courseComponent, Term term);
}
