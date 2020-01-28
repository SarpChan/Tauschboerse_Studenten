package de.hsrm.mi.swtpro.backend.service.pyScriptService;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils;

import de.hsrm.mi.swtpro.backend.controller.exceptions.PyScriptStorageException;
import de.hsrm.mi.swtpro.backend.model.Script;
import de.hsrm.mi.swtpro.backend.service.repository.PythonScriptRepository;

@Service
public class PyScriptStorageService{
    @Autowired
    PythonScriptRepository pyScriptRepo;

    public Script storeFile(final MultipartFile file) {
        final String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(filename.contains(".."))
                throw new PyScriptStorageException("Filename invalide" + filename);
            final Script pyScript = new Script(filename, file.getContentType(), file.getBytes());
            return pyScriptRepo.save(pyScript);
        } catch (final IOException e) {
            throw new PyScriptStorageException("File konnte nicht gespeichert werden", e);
        }
    }

    public Script getFile( long id) throws FileNotFoundException {
        return pyScriptRepo.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File wurde nicht gefunden " + id));
    }

    public void deletFile( long id) {

        if(pyScriptRepo.findById(id).isPresent())
            pyScriptRepo.deleteById(id);
           
    }
}