package de.hsrm.mi.swtpro.backend.service.messagebroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;

@Controller
public class Test {

    @Autowired
    MessageSender ms;

    @GetMapping("/test")
    public void send () {
        SwapOffer s = SwapOffer.builder().student(new Student()).fromGroup(new Group()).toGroup(new Group()).build();
        
        ms.sendSwapOfferMessage(s);
    }
}
