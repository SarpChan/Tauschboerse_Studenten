package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.UserNotFoundException;
import de.hsrm.mi.swtpro.backend.controller.exceptions.UserNotFoundException;
import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/rest")
public class UserCrudController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/user/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) throws URISyntaxException {
        userRepository.save(user);
        return user;
    }

    @PostMapping(path = "/user/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user) throws UserNotFoundException {
        return userRepository.save(user);

    }

    @GetMapping(path = "/user/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUser(@RequestParam("loginName") String loginName) throws UserNotFoundException {
        if (userRepository.findByLoginName(loginName) != null) {
            return userRepository.findByLoginName(loginName);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @DeleteMapping(path = "/user/delete", consumes = "application/json")
    public void deleteUser(@RequestBody User user) throws UserNotFoundException {
        if (userRepository.findByLoginName(user.getLoginName()) != null) {
            userRepository.delete(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

}
