package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.GroupNotFoundException;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class GroupCrudController {

    @Autowired
    GroupRepository groupRepository;

    @PostMapping(path = "/group/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Group createGroup(@RequestBody Group group) throws URISyntaxException {
        groupRepository.save(group);
        return group;
    }

    @PostMapping(path = "/group/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public Group updateGroup(@RequestBody Group group) throws GroupNotFoundException {
        return groupRepository.save(group);

    }

    @GetMapping(path = "/group/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public Group findGroup(@RequestParam("groupID") long groupID) throws GroupNotFoundException {
        if (groupRepository.findById(groupID) != null) {
            return groupRepository.findById(groupID);
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

    @DeleteMapping(path = "/group/delete", consumes = "application/json")
    public void deleteGroup(@RequestBody Group group) throws GroupNotFoundException {
        if (groupRepository.findById(group.getId()) != null) {
            groupRepository.delete(group);
        } else {
            throw new GroupNotFoundException("Group not found");
        }
    }

}
