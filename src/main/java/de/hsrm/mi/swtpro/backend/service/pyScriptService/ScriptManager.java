package de.hsrm.mi.swtpro.backend.service.pyScriptService;

import de.hsrm.mi.swtpro.backend.model.Script;
import de.hsrm.mi.swtpro.backend.service.repository.PythonScriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles persisted python scripts
 */
@Service
public class ScriptManager {

    @Autowired
    PythonScriptRepository repository;

    /**
     * Loads all matching scripts for an explicit method call identifier
     * @param identifier python script needs to contain this identifier
     * @return list of all scripts containing this identifier
     */
    public List<Script> loadAllMatchingScriptsFor(String identifier) {
        List<Script> scripts = repository.findAll();
        scripts = filterScriptsFor(scripts, identifier);
        Collections.shuffle(scripts);
        return scripts;
    }

    private List<Script> filterScriptsFor(List<Script> scripts, String identifier) {
        return scripts.stream().filter(script ->
                new String(script.getData()).contains(identifier)
        ).collect(Collectors.toList());
    }

}
