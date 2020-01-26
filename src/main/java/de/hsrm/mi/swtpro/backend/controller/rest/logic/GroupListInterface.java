package de.hsrm.mi.swtpro.backend.controller.rest.logic;


import de.hsrm.mi.swtpro.backend.controller.exceptions.GroupNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.login.security.JwtAuthentication;
import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.GroupCrudController;
import de.hsrm.mi.swtpro.backend.controller.rest.crud.StudentCrudController;
import de.hsrm.mi.swtpro.backend.model.CourseComponent;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.service.SwapOfferService;
import de.hsrm.mi.swtpro.backend.service.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    @GetMapping(path = "/group/dropdowncollection/{groupID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Student collectGroupsForDropdown(HttpServletRequest request,@PathVariable long groupID) throws ServletException, IOException, GroupNotFoundException {
        //final String requestHeader = request.getHeader(this.tokenHeader);
        CourseComponent selectedComponent = null;
        //   List<Group> groupsInComponent = groupRepository.findByCourseComponent(selectedComponent);
        /*    Group attendedGroup = student.getGroups().stream()
                    .filter(g -> g.getCourseComponent().equals(selectedComponent))
                    .findAny().get();
            groupsInComponent.remove(attendedGroup);*/
        //     return selectedComponent;
        Student student = studentRepository.findByUser(userRepository.findByLoginName("tthiel").get()).get();

       /* if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String authenticationToken = requestHeader.substring(7);
            logger.warn("TOKEN: " + tokenService.getUsernameFromToken(authenticationToken));

            selectedComponent = groupRepository.findById(groupID).get().getCourseComponent();

        }*/

        return student;


    }

/*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }*/
}
