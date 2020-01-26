package de.hsrm.mi.swtpro.backend.service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class PythonEvaluator {

    private ScriptEngine engine;

    public PythonEvaluator() {
        engine = new ScriptEngineManager().getEngineByName("python");
    }
}
