package de.hsrm.mi.swtpro.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/example")
public class Example {
    // http://localhost:8080/example/page
    @GetMapping("/page")
    @ResponseBody
    public String freundlichGruss() {
        return "<h1>Example!</h1>";
    }
}