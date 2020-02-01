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

/**
 * Crud-Controller with all POST- und GET-Methods for Python-Scripts
 */
@RestController
@RequestMapping("/rest")
public class PythonScriptCrudController{
    @Autowired 
    PyScriptStorageService pyScriptService;

    /**
     * POST-Method to uload a new Python-Script and insert it into the DB
     * @param file Script which the user want to upload and save 
     * @return filename and downloadUri
     * @throws URISyntaxException
     */
    @PostMapping(path="/pyScript/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadScript(@RequestParam("file") MultipartFile file) throws URISyntaxException {
         Script script = pyScriptService.storeFile(file);

         String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/pyScript/download/")
                .path(Long.toString(script.getId())).toUriString();

        return script.getFileName() + downloadUri;
    }

    /**
     * GET-Methode to download a new python script
     * @param fileId id from the script wht has to be downloaded
     * @return responseEntity
     * @throws FileNotFoundException
     */
    @GetMapping(path="/pyScript/download/{fileId}",  consumes = "application/json")
    public ResponseEntity<Resource> downloadFile(@PathVariable  long fileId) throws FileNotFoundException {
        // Lädt file von der DB
         Script script = pyScriptService.getFile(fileId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(script.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + script.getFileName() + "\"")
                .body(new ByteArrayResource(script.getData()));
    }

    /**
     * Remove a Script object from the Model
     *
     * @param scriptId id from the script what has to be deleted
     * @return void
     */
    @DeleteMapping(path = "/script/delete/{scriptId}", consumes = "application/json")
    public void deleteModule(@PathVariable final long scriptId) {
        pyScriptService.deleteFile(scriptId);
    }
}