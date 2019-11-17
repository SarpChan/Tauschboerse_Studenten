package de.hsrm.mi.swtpro.backend.service;

import  de.hsrm.mi.swtpro.backend.model.*;
import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

@Component
public class JSONGenerator {

    @PostConstruct
    private void init() {
        University uni = new University("Hochschule RheinMain", "Kurt-Schuhmacher-Ring 18");
        Campus ude = new Campus("Unter den Eichen", "Unter den Eichen 6", uni);
        uni.getCampus().add(ude);
        System.out.println(ude.getId());
        Building dBuilding = new Building("Gebäude D", ude);
        ude.getBuildings().add(dBuilding);
        Room r14 = new Room(14, 60, dBuilding);
        dBuilding.getRooms().add(r14);


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("uni.json"), uni);
        } catch (IOException e) {
            e.printStackTrace();
            //System.out.println("JSON new File Error");
        }
    }
}