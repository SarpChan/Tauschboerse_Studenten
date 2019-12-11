package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public Optional<Student> findByEnrollmentNumber(int enrolmentNumber);

    public void delete(Student student);

    public void deleteByEnrollmentNumber(int enrolmentNumber);
}
