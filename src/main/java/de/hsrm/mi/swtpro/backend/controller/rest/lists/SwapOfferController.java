package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGenerator;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGetter;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest/lists")
public class SwapOfferController {

    @Autowired
    TokenService tokenService;
    @Autowired
    ServiceGenerator serviceGenerator;
    @Autowired
    ServiceGetter serviceGetter;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    GroupRepository groupRepository;

    @PostMapping(path = "/createswapoffer", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DisplayCourseComponent> getSwapOfferCourses(HttpServletRequest request) {
        Student student = serviceGetter.getStudentFromUsername(tokenService.getUsernameFromRequest(request));

        return serviceGenerator.swapOfferCoursesFromStudent(student);
    }
}