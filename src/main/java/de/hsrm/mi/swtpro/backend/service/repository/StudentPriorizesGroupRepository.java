package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.StudentPriorizesGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentPriorizesGroupRepository extends JpaRepository<StudentPriorizesGroup,Long> {
    public List<StudentPriorizesGroup> findByStudent(Student student);
    public List<StudentPriorizesGroup> findByGroup(Group group);
    public List<StudentPriorizesGroup> findByPriority(int priority);
}
