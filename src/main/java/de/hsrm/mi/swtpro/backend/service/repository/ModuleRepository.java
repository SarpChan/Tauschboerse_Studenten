package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.ModuleInCurriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long> {
    List<Module> findByTitle(String title);
    List<Module> findByCreditPoints(int creditPoints);
    List<Module> findByCourses(Course course);
    List<Module> findByPeriod(int period);
    List<Module> findByModulesInCurriculum(ModuleInCurriculum moduleInCurriculum);
}
