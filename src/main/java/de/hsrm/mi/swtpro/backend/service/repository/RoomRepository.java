package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Long> {
    List<Room> findByNumber(int number);
    List<Room> findBySeats(int seats);
    List<Room> findByBuilding(Building building);
    List<Room> findByGroups(Group group);

}
