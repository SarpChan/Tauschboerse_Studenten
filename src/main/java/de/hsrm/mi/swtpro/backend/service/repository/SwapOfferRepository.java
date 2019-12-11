package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface SwapOfferRepository extends JpaRepository<SwapOffer,Long> {
    public List<SwapOffer> findByFromGroup(Group group);
    public List<SwapOffer> findByToGroup(Group group);
    public List<SwapOffer> findByStudent(Student student);
    public List<SwapOffer> findByDate(Timestamp date);
}
