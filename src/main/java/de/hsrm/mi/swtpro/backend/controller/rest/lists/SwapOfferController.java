package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGenerator;
import de.hsrm.mi.swtpro.backend.service.helper.ServiceGetter;
import de.hsrm.mi.swtpro.backend.service.repository.GroupRepository;
import de.hsrm.mi.swtpro.backend.service.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller containing endpoint for available swap offers for a specific user
 */
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

    /**
     * GET request for available swap offers of a specific user
     * @param request to extract username from
     * @return all swap offers of a specific user
     */
    @GetMapping(path = "/availableSwaps", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DisplayCourseComponent> getAvailableSwaps(HttpServletRequest request) {
        Student student = serviceGetter.getStudentFromUsername(tokenService.getUsernameFromRequest(request));

        return serviceGenerator.swapOfferCoursesFromStudent(student);
    }
}