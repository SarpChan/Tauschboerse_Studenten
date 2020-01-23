package de.hsrm.mi.swtpro.backend.service.messagebroker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import de.hsrm.mi.swtpro.backend.model.Group;
import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.SwapOffer;

@Controller
public class Test {

    @Autowired
    MessageSender ms;

    @GetMapping("/test")
    public void send () {
        List<String> l = new ArrayList<>();
        l.add("1");
        //ms.addSwapMessageQueue(l);
        SwapOffer s = SwapOffer.builder().student(new Student()).fromGroup(new Group()).toGroup(new Group()).build();
        System.out.println("IM HERE");
        ms.sendPersonalSwapOfferMessage(s, "1");
    }
}
