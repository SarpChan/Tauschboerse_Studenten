package de.hsrm.mi.swtpro.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    @Autowired
    ModuleRepository moduleDatabase; //HABEN NOCH KEIN JPA REP ? Haben wir was anderes?

    public Module findModuleById(int id) {
        return moduleDatabase.findByID(id);
        /* In ModuleRepository.java:
        *   Module findByID(int id);
        *
        */
    }
}