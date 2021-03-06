package de.hsrm.mi.swtpro.backend.controller.rest.crud;


import de.hsrm.mi.swtpro.backend.controller.exceptions.GroupNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class GroupCrudController {

    @Autowired
    GroupRepository groupRepository;

    /**
     * Insert a Group object into the Model
     *
     * @param group recieves a Group class via POST request
     * @return Group object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/group/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Group createGroup(@RequestBody Group group) throws URISyntaxException {
        groupRepository.save(group);
        return group;
    }

    /**
     * Update a Group object into the Model
     * @param group recieves a Group class via POST request
     * @return Group object
     * @throws GroupNotFoundException
     */
    @PostMapping(path = "/group/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Group updateGroup(@RequestBody Group group) throws GroupNotFoundException {
        return groupRepository.save(group);

    }

    /**
     * Find a Group object from the Model
     *
     * @param groupID recieves key from group
     * @return Group object
     * @throws GroupNotFoundException
     */
    @GetMapping(path = "/group/read/{groupID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Group findGroup(@PathVariable long groupID) throws GroupNotFoundException {
        if (groupRepository.findById(groupID).isPresent()) {
            return groupRepository.findById(groupID).get();
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

    /**
     * Remove a Group object from the Model
     *
     * @param group recieves a Group class via DELETE request
     * @return void
     * @throws GroupNotFoundException
     */
    @DeleteMapping(path = "/group/delete", consumes = "application/json")
    public void deleteGroup(@PathVariable long group) throws GroupNotFoundException {
        if (groupRepository.findById(group).isPresent()) {
            groupRepository.deleteById(group);
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

}
