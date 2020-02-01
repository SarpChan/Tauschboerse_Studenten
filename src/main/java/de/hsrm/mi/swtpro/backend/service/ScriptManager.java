package de.hsrm.mi.swtpro.backend.service;

import de.hsrm.mi.swtpro.backend.service.repository.PythonScriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScriptManager {

    @Autowired
    PythonScriptRepository repository;

}
