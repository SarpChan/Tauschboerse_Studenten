package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Course;
import de.hsrm.mi.swtpro.backend.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TermRepository extends JpaRepository<Term,Long> {
    public List<Term> findByPeriod(int period);
    public  List<Term> findByCourses(Course course);
    public  List<Term> findByStartDate(Date date);
    public List<Term> findByEndDate(Date date);
}
