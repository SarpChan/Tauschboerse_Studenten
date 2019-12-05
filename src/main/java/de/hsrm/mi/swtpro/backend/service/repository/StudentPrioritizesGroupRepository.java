package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentPrioritizesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPrioritizesGroupRepository extends JpaRepository<StudentPrioritizesGroup,Long> {
    List<StudentPrioritizesGroup> findByStudent(Student student);
    List<StudentPrioritizesGroup> findByGroup(Group group);
    List<StudentPrioritizesGroup> findByPriority(int priority);
}
