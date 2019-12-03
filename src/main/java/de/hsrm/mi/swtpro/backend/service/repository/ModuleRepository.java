package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long> {
    public List<Module> findByTitle(String title);
    public List<Module> findByCreditPoints(int creditPoints);
    public List<Module> findByCourses(Course course);
    public List<Module> findByPeriod(int period);
}
