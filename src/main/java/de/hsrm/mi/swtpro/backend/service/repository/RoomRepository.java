package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository  extends JpaRepository<Room, Long> {
    public Room findById(long id);
    public List<Room> findByNumber(int number);
    public List<Room> findBySeats(int seats);
    public List<Room> findByBuilding(Building building);
}
