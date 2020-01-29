package de.hsrm.mi.swtpro.backend.controller.rest;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import de.hsrm.mi.swtpro.backend.model.Script;
import de.hsrm.mi.swtpro.backend.service.pyScriptService.PyScriptStorageService;

@RestController
@RequestMapping("/rest")
public class PythonScriptCrudController{
    @Autowired 
    PyScriptStorageService pyScriptService;

    @PostMapping(path="/pyScript/upload", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadScript(@RequestParam("file") final MultipartFile file) throws URISyntaxException {
        final Script script = pyScriptService.storeFile(file);

        final String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/pyScript/upload/")
                .path(Long.toString(script.getId())).toUriString();

        return script.getFileName() + downloadUri;
    }

    @GetMapping(path="/pyScript/download/{fileId}",  consumes = "application/json")
    public ResponseEntity<Resource> downloadFile(@PathVariable final long fileId) throws FileNotFoundException {
        // Load file from database
        final Script script = pyScriptService.getFile(fileId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(script.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + script.getFileName() + "\"")
                .body(new ByteArrayResource(script.getData()));
    }

    /**
     * Remove a Script object from the Model
     *
     * @param scriptId recieves a Module class via DELETE request
     * @return void
     */
    @DeleteMapping(path = "/script/delete/{scriptId}", consumes = "application/json")
    public void deleteModule(@PathVariable final long scriptId) {
        pyScriptService.deletFile(scriptId);
    }
}