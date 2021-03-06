package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository-Klasse für Python-Scripts, die der User hochladen und speichern kann
 */
@Repository
public interface PythonScriptRepository extends JpaRepository<Script, Long> {
    List<Script> findByUserId(long userId);
    List<Script> findByFileName(String title);

}