package de.hsrm.mi.swtpro.backend.controller.rest.logic;


import de.hsrm.mi.swtpro.backend.controller.exceptions.GroupNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.GroupCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.StudentCrudController;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.service.SwapOfferService;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGetter;
import de.hsrm.mi.swtpro.backend.service.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * REST endpoint for selectable groups per selected module.
 * Logic not seperated into a dedicated service class.
 */
@RestController
@RequestMapping("/rest")
public class GroupListInterface {
    Logger logger = LoggerFactory.getLogger(GroupListInterface.class);
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SwapOfferRepository swapOfferRepository;
    @Autowired
    ModuleRepository moduleCrudController;
    @Autowired
    StudentCrudController studentCrudController;
    @Autowired
    GroupCrudController groupCrudController;
    @Autowired
    TokenService tokenService;
    @Autowired
    ServiceGetter serviceGetter;
    @Autowired
    SwapOfferService swapOfferService;

    @Value("${security.jwt.token.header:Authorization}")
    private String tokenHeader;

    /**
     * Filtering multiple times through groups and retrieving available groups for the selected module (=> found inside the group=
     * @param request
     * @param groupID
     * @return Key:Value  ->  GroupID:GroupCharacter
     * @throws ServletException
     * @throws IOException
     * @throws GroupNotFoundException
     */
    @GetMapping(path = "/group/dropdowncollection/{groupID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<Long,Character> collectGroupsForDropdown(HttpServletRequest request, @PathVariable long groupID) throws ServletException, IOException, GroupNotFoundException {
        String username = tokenService.getUsernameFromRequest(request);
        Student student = serviceGetter.getStudentFromUsername(username);
        HashMap<Long,Character> groupsInComponent = new HashMap<Long,Character>();
        logger.warn("TOKEN: " + username);
        CourseComponent selectedComponent = groupRepository.findById(groupID).get().getCourseComponent();
        Set<Group> groupsList = selectedComponent.getGroups();
        groupsList.stream()
                .forEach(e -> {
                    groupsInComponent.put(e.getId(),e.getGroupChar());
                    logger.warn("Kurs: " + e.getCourseComponent().getCourse().getTitle() + " Typ: " + e.getCourseComponent().getType().name() + " Gruppe: " + e.getGroupChar() + " - " + e.getId());
                });
        Long attendedGroupId = null;
        if (student.getGroups().stream()
                .filter(g -> g.getCourseComponent().equals(selectedComponent))
                .findAny()
                .isPresent()) {
            attendedGroupId = student.getGroups().stream()
                    .filter(g -> g.getCourseComponent().equals(selectedComponent))
                    .findAny().get().getId();
        }
        assert attendedGroupId != null:username + " does not attend in " +
                "Kurs: " + selectedComponent.getCourse().getTitle() + " Typ: " + selectedComponent.getType().name();

        if (groupsInComponent.containsKey(attendedGroupId)) {
            groupsInComponent.remove(student.getGroups().stream()
                    .filter(g -> g.getCourseComponent().equals(selectedComponent))
                    .findAny().get().getId());
        } else {
            throw new GroupNotFoundException(username + " does not attend in " +
                    "Kurs: " + selectedComponent.getCourse().getTitle() + " Typ: " + selectedComponent.getType().name());
        }
        logger.warn("RESULT: " + groupsInComponent);

        return groupsInComponent;
            
    }
}
