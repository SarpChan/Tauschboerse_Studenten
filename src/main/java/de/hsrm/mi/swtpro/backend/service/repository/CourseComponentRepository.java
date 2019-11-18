package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseComponentRepository extends JpaRepository<CourseComponent,Long> {

    public List<CourseComponent> findByType(CourseType type );
    public List<CourseComponent> findByCreditPoints(int creditPoints);
    public List<CourseComponent> findByExam(String exam);
}
