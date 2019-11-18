package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.ExamRegulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExamRegulationRepository extends JpaRepository<ExamRegulation,Long> {

    public List<ExamRegulation> findByDate(Date date);
    public List<ExamRegulation> findByRule(int rule);
}
