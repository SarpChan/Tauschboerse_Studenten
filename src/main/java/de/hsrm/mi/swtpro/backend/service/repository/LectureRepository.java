package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Lecturer;
import de.hsrm.mi.swtpro.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecturer,Long> {
    List<Lecturer> findByPriviledge(int privilege);
    List<Lecturer> findByGroups(Group group);
    List<Lecturer> findByUser(User user);

}
