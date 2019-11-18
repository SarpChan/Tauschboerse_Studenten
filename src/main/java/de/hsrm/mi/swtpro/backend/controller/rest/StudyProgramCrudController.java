package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.StudyProgramNotFoundException;
import de.hsrm.mi.swtpro.backend.model.StudyProgram;
import de.hsrm.mi.swtpro.backend.service.repository.StudyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class StudyProgramCrudController {

    @Autowired
    StudyProgramRepository studyprogramRepository;

    @PostMapping(path = "/studyprogram/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudyProgram createStudyProgram(@RequestBody StudyProgram studyprogram) throws URISyntaxException {
        studyprogramRepository.save(studyprogram);
        return studyprogram;
    }

    @PostMapping(path = "/studyprogram/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudyProgram updateStudyProgram(@RequestBody StudyProgram studyprogram) throws StudyProgramNotFoundException {
        return studyprogramRepository.save(studyprogram);

    }

    @GetMapping(path = "/studyprogram/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudyProgram findStudyProgram(@RequestParam("studyprogramID") long studyprogramID) throws StudyProgramNotFoundException {
        if (studyprogramRepository.findById(studyprogramID) != null) {
            return studyprogramRepository.findById(studyprogramID);
        } else {
            throw new StudyProgramNotFoundException("StudyProgram not found");
        }
    }

    @DeleteMapping(path = "/studyprogram/delete", consumes = "application/json")
    public void deleteStudyProgram(@RequestBody StudyProgram studyprogram) throws StudyProgramNotFoundException {
        if (studyprogramRepository.findById(studyprogram.getId()) != null) {
            studyprogramRepository.delete(studyprogram);
        } else {
            throw new StudyProgramNotFoundException("StudyProgram not found");
        }
    }

}
