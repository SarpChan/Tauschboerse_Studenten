package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.RoomNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Room;
import de.hsrm.mi.swtpro.backend.service.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class RoomCrudController {

    @Autowired
    RoomRepository roomRepository;

    /**
     * Insert a Room object into the Model
     *
     * @param room recieves a Room class via POST request
     * @return Room object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/room/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room createRoom(@RequestBody Room room) throws URISyntaxException {
        roomRepository.save(room);
        return room;
    }

    /**
     * Update a Room object into the Model
     * @param room recieves a Room class via POST request
     * @return Room object
     * @throws RoomNotFoundException
     */
    @PostMapping(path = "/room/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room updateRoom(@RequestBody Room room) throws RoomNotFoundException {
        return roomRepository.save(room);

    }

    /**
     * Find a Room object from the Model
     * @param roomID recieves key from room
     * @return Room object
     * @throws RoomNotFoundException
     */
    @GetMapping(path = "/room/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Room findRoom(@RequestParam("roomID") long roomID) throws RoomNotFoundException {
        if (roomRepository.findById(roomID) != null) {
            return roomRepository.findById(roomID);
        } else {
            throw new RoomNotFoundException("Room not found");
        }
    }

    /**
     * Remove a Room object from the Model
     * @param room recieves a Room class via POST request
     * @return Room object or
     * @throws RoomNotFoundException
     */
    @DeleteMapping(path = "/room/delete", consumes = "application/json")
    public void deleteRoom(@RequestBody Room room) throws RoomNotFoundException {
        if (roomRepository.findById(room.getId()) != null) {
            roomRepository.delete(room);
        } else {
            throw new RoomNotFoundException("Room not found");
        }
    }

}
