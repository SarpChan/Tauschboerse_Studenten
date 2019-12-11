package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Role;
import de.hsrm.mi.swtpro.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginName(String loginName);
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    List<User> findByRoles(Role role);

}
