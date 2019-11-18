package de.hsrm.mi.swtpro.backend.model.repositories;

import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository<Student> extends PagingAndSortingRepository<Student,Integer> {
    public Student findByID(int matrNr);
}
