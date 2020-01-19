package de.hsrm.mi.swtpro.backend.controller.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import de.hsrm.mi.swtpro.backend.controller.login.security.JwtAuthenticationEntryPoint;
import de.hsrm.mi.swtpro.backend.controller.login.security.JwtAuthenticationProvider;
import de.hsrm.mi.swtpro.backend.controller.login.security.JwtAuthenticationTokenFilter;

/**
 * Web security config class for the security configurations
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * configure AuthenticationManager so that it knows from where to load user for
     * matching credentials use PasswordEncoder
     * 
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
        authenticationManagerBuilder.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);

    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

            httpSecurity.csrf().disable()
          
                .authorizeRequests().antMatchers("/authentication/**", "/h2-console/**", "/").permitAll()
                .antMatchers("/**" ).hasRole("ADMIN")		
                .antMatchers("/**").hasRole("USER")			
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
        httpSecurity.headers().frameOptions().disable();
    }
}