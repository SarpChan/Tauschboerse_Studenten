package de.hsrm.mi.swtpro.backend.model.repositories;

import de.hsrm.mi.swtpro.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URISyntaxException;

public interface StudentRepository extends PagingAndSortingRepository<Student,Integer> {
    public Student findByEnrolementNumber(int enrollmentNumber);
    public void delete(Student student);
}
