package de.hsrm.mi.swtpro.backend.service.helper;

import de.hsrm.mi.swtpro.backend.model.Student;
import de.hsrm.mi.swtpro.backend.model.Term;
import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.service.repository.StudentRepository;
import de.hsrm.mi.swtpro.backend.service.repository.TermRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceGetter {

    @Autowired
    TermRepository termRepository;
    /**
     * get the running term depending on current date
     * @return current term
     */
    public Term getCurrentTerm() {
        Date today = Calendar.getInstance().getTime();
        List<Term> terms = termRepository.findByEndDateBefore(today);
        return terms.get(0);
    }

    @Autowired
    UserRepository userRepository;
    /**
     * returns the User object with given username
     * @param username
     * @return user
     */
    public User getUserFromUsername(String username) {
        if(username != null) {
            Optional<User> users = userRepository.findByLoginName(username);

            if (users.isPresent()) {
                return users.get();
            }
        }
        return null;
    }

    @Autowired
    StudentRepository studentRepository;
    /**
     * returns Student objekt that belongs to given User
     * @param user
     * @return student
     */
    public Student getStudentFromUser(User user) {
        if(user != null) {
            Optional<Student> students = studentRepository.findByUser(user);

            if (students.isPresent()) {
                return students.get();
            }
        }
        return null;
    }

    /**
     * returns Student objekt with given username
     * @param username
     * @return student
     */
    public Student getStudentFromUsername(String username) {
        return getStudentFromUser(getUserFromUsername(username));
    }
}
