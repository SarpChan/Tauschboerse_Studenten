package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    public List<Course> findByTitle(String name);
    public List<Course> findByCourseComponents(CourseComponent courseComponent);
    public List<Course> findByModules(Module module);
    public List<Course> findByOwner(User user);
    public List<Course> findByTerms(Term term);
}
