package de.hsrm.mi.swtpro.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hsrm.mi.swtpro.backend.model.University;
import de.hsrm.mi.swtpro.backend.service.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

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

    @PostConstruct
    public void fillDatabase(){
        /*String[] args = appArgs.getSourceArgs();
        for (int i = 0; i < args.length ; i++) {
            if(args[i].equals("fillDatabase")){*/
        //readJSON(jsonGenerator.createJSON());
        File file = new File("hsrm_medieninformatik.json");
        //readJSON(file);
            //}
        //}
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
