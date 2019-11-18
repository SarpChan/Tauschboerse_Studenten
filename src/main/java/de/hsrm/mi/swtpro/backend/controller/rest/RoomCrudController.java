package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.RoomNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Room;
import de.hsrm.mi.swtpro.backend.service.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class RoomCrudController {

    @Autowired
    RoomRepository roomRepository;

    @PostMapping(path = "/room/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room createRoom(@RequestBody Room room) throws URISyntaxException {
        roomRepository.save(room);
        return room;
    }

    @PostMapping(path = "/room/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room updateRoom(@RequestBody Room room) throws RoomNotFoundException {
        return roomRepository.save(room);

    }

    @GetMapping(path = "/room/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room findRoom(@RequestParam("roomID") long roomID) throws RoomNotFoundException {
        if (roomRepository.findById(roomID) != null) {
            return roomRepository.findById(roomID);
        } else {
            throw new RoomNotFoundException("Room not found");
        }
    }

    @DeleteMapping(path = "/room/delete", consumes = "application/json")
    public void deleteRoom(@RequestBody Room room) throws RoomNotFoundException {
        if (roomRepository.findById(room.getId()) != null) {
            roomRepository.delete(room);
        } else {
            throw new RoomNotFoundException("Room not found");
        }
    }

}
