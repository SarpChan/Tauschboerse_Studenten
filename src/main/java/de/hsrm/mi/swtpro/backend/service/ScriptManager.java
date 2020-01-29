package de.hsrm.mi.swtpro.backend.service;

import de.hsrm.mi.swtpro.backend.model.Script;
import de.hsrm.mi.swtpro.backend.service.repository.PythonScriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScriptManager {

    @Autowired
    PythonScriptRepository repository;

    public List<Script> loadAllMatchingScriptsFor(String identifier) {
        List<Script> scripts = repository.findAll();
        filterScriptsFor(scripts, identifier);
        Collections.shuffle(scripts);
        return scripts;
    }

    private void filterScriptsFor(List<Script> scripts, String identifier) {
        scripts = scripts.stream().filter(script ->
                new String(script.getData()).contains(identifier)
        ).collect(Collectors.toList());
    }

}
