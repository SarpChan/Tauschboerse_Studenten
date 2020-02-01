package de.hsrm.mi.swtpro.backend.controller.rest.crud;


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
     *
     * @param userID recieves key from user
     * @return User object
     * @throws UserNotFoundException
     */
    @GetMapping(path = "/user/read/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User findUser(@PathVariable long userID) throws UserNotFoundException {
        if (userRepository.findById(userID).isPresent()) {
            return userRepository.findById(userID).get();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Remove a User object from the Model
     *
     * @param userID recieves a User id via DELETE request
     * @return void
     * @throws UserNotFoundException
     */
    @DeleteMapping(path = "/user/delete/{userID}", consumes = "application/json")
    public void deleteUser(@PathVariable long userID) throws UserNotFoundException {
        if (userRepository.findById(userID).isPresent()) {
            userRepository.deleteById(userID);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

}
