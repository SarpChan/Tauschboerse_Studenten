package de.hsrm.mi.swtpro.backend.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.swtpro.backend.services.DatabaseService;


/**
 * Bis jetzt alles Platzhalterkram
 */
@RestController
@RequestMapping("/") //KEIN PLAN BIS JETZT WO WIE WAS (TOBI CRUD?)
public class TimetableRestController {
   @Autowired
   DatabaseService databaseService; //KEIN PLAN, WER MACHT DEN DATABASE SERVICE? AUCH TOBI/CRUD?

    @GetMapping("/timetable") //URL UNBEKANNT
    public List<Module> getModulesForUser(){ // SOMEWHERE A WILD JSON SHOULD APPEAR
        List<Module> moduleList = new ArrayList<Module>();
        for (int id: idList) {
            moduleList.add(databaseService.findModuleById(id));
        }
        return moduleList; // HIER SOLLTE WOHL JSON ZURÜCKGEGEBEN WERDEN?
    }
}
