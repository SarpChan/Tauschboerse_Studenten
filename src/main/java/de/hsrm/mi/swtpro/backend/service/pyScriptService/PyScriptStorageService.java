package de.hsrm.mi.swtpro.backend.service.pyScriptService;

import de.hsrm.mi.swtpro.backend.controller.exceptions.PyScriptStorageException;
import de.hsrm.mi.swtpro.backend.model.Script;
import de.hsrm.mi.swtpro.backend.service.repository.PythonScriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class PyScriptStorageService {
    @Autowired
    PythonScriptRepository pyScriptRepo;

    public Script storeFile(final MultipartFile file) {
        final String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (filename.contains(".."))
                throw new PyScriptStorageException("Filename invalide" + filename);
            final Script pyScript = Script.builder()
                    .fileName(filename)
                    .fileType(file.getContentType())
                    .data(file.getBytes())
                    .build();
            return pyScriptRepo.save(pyScript);
        } catch (final IOException e) {
            throw new PyScriptStorageException("File konnte nicht gespeichert werden", e);
        }
    }

    public Script getFile(long id) throws FileNotFoundException {
        return pyScriptRepo.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File wurde nicht gefunden " + id));
    }

    public void deleteFile(long id) {

        if (pyScriptRepo.findById(id).isPresent())
            pyScriptRepo.deleteById(id);

    }
}