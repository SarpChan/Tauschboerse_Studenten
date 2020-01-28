package de.hsrm.mi.swtpro.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Component
public class PythonEvaluator {

    private ScriptEngine engine;

    @Autowired
    private ServerApi serverApi;

    public PythonEvaluator() {
        engine = new ScriptEngineManager().getEngineByName("python");
    }

    public void runScriptForSwapOffer(String script) {
        try {
            if(script.contains("def onNewSwapOffer():")) {
                engine.put("serverApi",serverApi);
                engine.eval(script);
                engine.eval("onNewSwapOffer()");
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
