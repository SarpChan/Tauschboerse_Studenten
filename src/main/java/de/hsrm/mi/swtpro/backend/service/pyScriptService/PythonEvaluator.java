package de.hsrm.mi.swtpro.backend.service.pyScriptService;

import de.hsrm.mi.swtpro.backend.model.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Python script evaluation class.
 * Runs python scripts for backend
 */
@Service
public class PythonEvaluator {

    private ScriptEngine engine;

    @Autowired
    private ServerApi serverApi;

    /**
     * Constructor of python evaluator
     * Initializes python script engine
     */
    public PythonEvaluator() {
        engine = new ScriptEngineManager().getEngineByName("python");
    }

    /**
     * Runs python code of given script
     * Method runs python code of given script and checks for explicit
     * function calls
     * @param script containing runnable script
     */
    public void runScriptForSwapOffer(Script script) {
        String data = new String(script.getData());
        try {
            if (data.contains("def onNewSwapOffer():")) {
                engine.put("ownId", script.getUserId());
                engine.put("serverApi", serverApi);
                engine.eval(data);
                engine.eval("\nonNewSwapOffer()");
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
