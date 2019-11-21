package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.StudyProgramNotFoundException;
import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import de.hsrm.mi.swtpro.backend.service.repository.StudyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class StudyProgramCrudController {

    @Autowired
    StudyProgramRepository studyprogramRepository;

    /**
     * Insert a StudyProgram object into the Model
     *
     * @param studyprogram recieves a StudyProgram class via POST request
     * @return StudyProgram object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/studyprogram/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudyProgram createStudyProgram(@RequestBody StudyProgram studyprogram) throws URISyntaxException {
        studyprogramRepository.save(studyprogram);
        return studyprogram;
    }

    /**
     * Update a StudyProgram object into the Model
     * @param studyprogram recieves a StudyProgram class via POST request
     * @return StudyProgram object
     * @throws StudyProgramNotFoundException
     */
    @PostMapping(path = "/studyprogram/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudyProgram updateStudyProgram(@RequestBody StudyProgram studyprogram) throws StudyProgramNotFoundException {
        return studyprogramRepository.save(studyprogram);

    }

    /**
     * Find a StudyProgram object from the Model
     * @param studyprogramID recieves key from studyprogram
     * @return StudyProgram object
     * @throws StudyProgramNotFoundException
     */
    @GetMapping(path = "/studyprogram/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudyProgram findStudyProgram(@RequestParam("studyprogramID") long studyprogramID) throws StudyProgramNotFoundException {
        if (studyprogramRepository.findById(studyprogramID) != null) {
            return studyprogramRepository.findById(studyprogramID);
        } else {
            throw new StudyProgramNotFoundException("StudyProgram not found");
        }
    }

    /**
     * Remove a StudyProgram object from the Model
     * @param studyprogram recieves a StudyProgram class via POST request
     * @return StudyProgram object or
     * @throws StudyProgramNotFoundException
     */
    @DeleteMapping(path = "/studyprogram/delete", consumes = "application/json")
    public void deleteStudyProgram(@RequestBody StudyProgram studyprogram) throws StudyProgramNotFoundException {
        if (studyprogramRepository.findById(studyprogram.getId()) != null) {
            studyprogramRepository.delete(studyprogram);
        } else {
            throw new StudyProgramNotFoundException("StudyProgram not found");
        }
    }

}
