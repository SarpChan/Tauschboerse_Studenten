package de.hsrm.mi.swtpro.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swtpro.backend.model.*;
import de.hsrm.mi.swtpro.backend.model.Module;
import de.hsrm.mi.swtpro.backend.service.repository.StudyProgramRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.util.HashSet;

/**
 * The DatabseFiller uses the JSON from the JSONGenerator to fill the whole database for
 * testing purposes.
 */
@Component
public class DatabaseFiller {

    @Autowired
    ApplicationArguments appArgs;

    @Autowired
    JSONGenerator jsonGenerator;

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    EntityManagerFactory emf;
    @Autowired
    EntityManager entityManager;
    //@Autowired
    //UniversityRepository universityRepository;
    @Autowired
    StudyProgramRepository studyProgramRepository;


    @PostConstruct
    public void fillDatabase(){
        /*String[] args = appArgs.getSourceArgs();
        for (int i = 0; i < args.length ; i++) {
            if(args[i].equals("fillDatabase")){
                readJSON(jsonGenerator.createJSON());
            }
        }*/
        readJSON(jsonGenerator.createJSON());

    }

    public void readJSON(File universityJSON){
        try {
            University university
                    = new ObjectMapper().readerFor(University.class).readValue(universityJSON);
            universityRepository.saveAndFlush(university);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
