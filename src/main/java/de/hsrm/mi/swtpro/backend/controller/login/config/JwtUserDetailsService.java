package de.hsrm.mi.swtpro.backend.controller.login.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.model.User.UserBuilder;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByLoginName(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        User user = optionalUser.get();

        String userRights = user.getUserRights().toString();
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
          builder = org.springframework.security.core.userdetails.User.withUsername(username);
          builder.password(encoder.encode(user.getPassword()));
          builder.roles(userRights);
        }
        return builder.build();
    }
}