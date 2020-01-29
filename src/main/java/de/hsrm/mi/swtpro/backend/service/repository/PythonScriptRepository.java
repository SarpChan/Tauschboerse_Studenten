package de.hsrm.mi.swtpro.backend.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hsrm.mi.swtpro.backend.model.Script;

@Repository
public  interface PythonScriptRepository extends JpaRepository<Script,Long>{
    List<Script> findByUserId(long userId);
    List<Script> findByTitle(String title);

    
}