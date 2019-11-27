package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Room;
import de.hsrm.mi.swtpro.backend.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    public Group findById(long term);
    public List<Group> findByTerm(Term term);
    public List<Group> findByCourseComponent(CourseComponent courseComponent);
    public List<Group> findByRoom(Room room);
    public List<Group> findByDayOfWeek(DayOfWeek dayOfWeek);
    public List<Group> findByEndTime(LocalTime time);
    public List<Group> findByStartTime(LocalTime time);
    public List<Group> findBySlots(int slots);

    public List<Group> findByStartTimeAndDayOfWeek(LocalTime time, DayOfWeek dayOfWeek);
}
