package de.hsrm.mi.swtpro.backend.service.repository;

import de.hsrm.mi.swtpro.backend.model.Administrator;
import de.hsrm.mi.swtpro.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    List<Administrator> findByPriviledge(int priviliegde);
    List<Administrator> findByUser(User user);
}
