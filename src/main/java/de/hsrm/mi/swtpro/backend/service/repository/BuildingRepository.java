package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.model.Campus;
import de.hsrm.mi.swtpro.backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long>{
    List<Building> findByName(String name);
    Optional<Building> findByCampus(Campus campus);
    List<Building> findByRooms(Room room);

}
