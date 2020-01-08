package de.hsrm.mi.swtpro.backend.controller.login;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * the component PasswordEncoderConfigure 
 * contains a passwordencoder
 */
 @Component
public class PasswordEncoderConfigure{

    /**
     * generate a passwordencoder
     * @return passwordencoder
     */
    @Bean PasswordEncoder getPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
