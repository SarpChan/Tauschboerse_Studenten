package de.hsrm.mi.swtpro.backend.controller.rest;


import de.hsrm.mi.swtpro.backend.controller.exceptions.UserNotFoundException;
import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * Basic CRUD Controller for insert, update, read and delete functionality at the model.
 */
@RestController
@RequestMapping("/rest")
public class UserCrudController {

    @Autowired
    UserRepository userRepository;

    /**
     * Insert a User object into the Model
     *
     * @param user recieves a User class via POST request
     * @return User object
     * @throws URISyntaxException
     */
    @PostMapping(path = "/user/create", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) throws URISyntaxException {
        userRepository.save(user);
        return user;
    }

    /**
     * Update a User object into the Model
     * @param user recieves a User class via POST request
     * @return User object
     * @throws UserNotFoundException
     */
    @PostMapping(path = "/user/update", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user) throws UserNotFoundException {
        return userRepository.save(user);

    }

    /**
     * Find a User object from the Model
     * @param loginName recieves key from user
     * @return User object
     * @throws UserNotFoundException
     */
    @GetMapping(path = "/user/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUser(@RequestParam("loginName") String loginName) throws UserNotFoundException {
        if (userRepository.findByLoginName(loginName) != null) {
            return userRepository.findByLoginName(loginName);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Remove a User object from the Model
     * @param user recieves a User class via POST request
     * @return User object or
     * @throws UserNotFoundException
     */
    @DeleteMapping(path = "/user/delete", consumes = "application/json")
    public void deleteUser(@RequestBody User user) throws UserNotFoundException {
        if (userRepository.findByLoginName(user.getLoginName()) != null) {
            userRepository.delete(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

}
