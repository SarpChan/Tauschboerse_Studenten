package de.hsrm.mi.swtpro.backend.controller.rest.logic;

import de.hsrm.mi.swtpro.backend.controller.login.security.JwtAuthentication;
import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.GroupCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.StudentCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.UserCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.logic.SwapOfferInterface;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.service.SwapOfferService;
import de.hsrm.mi.swtpro.backend.service.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    SwapOfferService swapOfferService;

    @Value("${security.jwt.token.header:Authorization}")
    private String tokenHeader;


    @GetMapping(path = "/group/dropdowncollection/{groupID}")
    public void collectGroupsForDropdown(HttpServletRequest request, @PathVariable  long groupID) {
        final String requestHeader = request.getHeader(this.tokenHeader);

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String  authenticationToken = requestHeader.substring(7);
            JwtAuthentication authentication = new JwtAuthentication(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.warn("TOKEN: "+tokenService.getUsernameFromToken(authenticationToken));


        }
/*
        Student student = studentRepository.findById(id);
        CourseComponent selectedComponent =  groupRepository.findById(groupID).get().getCourseComponent();
        List<Group> groupsInComponent = groupRepository.findByCourseComponent(selectedComponent);
        Group attendedGroup = student.getGroups().stream()
                .filter(g -> g.getCourseComponent().equals(selectedComponent))
                .findAny().get();
        groupsInComponent.remove(attendedGroup);

        return groupsInComponent;*/

    }

}
