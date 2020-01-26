package de.hsrm.mi.swtpro.backend.controller.rest.lists;

import de.hsrm.mi.swtpro.backend.controller.login.security.TokenService;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.service.helper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/rest/lists")
public class ModuleController {

    @Autowired
    TokenService tokenService;
    @Autowired
    ServiceGenerator serviceGenerator;
    @Autowired
    ServiceGetter serviceGetter;

    @GetMapping(path = "/module/prioritize", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ModuleSelectionItem> getModuleItem(HttpServletRequest request) {

        String username = tokenService.getUsernameFromRequest(request);
        Student student = serviceGetter.getStudentFromUsername(username);
        Curriculum curriculum = student.getExamRegulation().getCurriculums().iterator().next();

//      List<Module> filteredModules = moduleRepository.findAll();
//      ModuleFilterFactory moduleFilterFactory = ModuleFilterFactory.builder().filters(filters).build();
//      filteredModules = moduleFilterFactory.filter(filteredModules);

        return serviceGenerator.moduleSelectionItemFromCurriculum(curriculum);
    }
}