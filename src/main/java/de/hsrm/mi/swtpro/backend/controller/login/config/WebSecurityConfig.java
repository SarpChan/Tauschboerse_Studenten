package de.hsrm.mi.swtpro.backend.controller.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
    PasswordEncoder passwordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

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

    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

            httpSecurity.csrf().disable()

                .authorizeRequests().antMatchers("/authentication/**", "/h2-console/**", "/actuator/**", "/test/**").permitAll()
                .antMatchers().hasAuthority("USER")
                .antMatchers("rest/building/**","rest/campus/**", "rest/coursecomponents/**", "rest/curriculum/**", "rest/examregulation/**", "rest/fieldofstudy/**" ,
                "rest/group/**", "rest/lecture/**", "rest/module/**", "rest/modulelnCurriculum/**", "rest/pyScribt/**", "rest/room/**", "rest/studentAttendsCourse/**", "rest/student/**",
                "rest/studentPassedExam/**", "rest/studentPrioritzesGroup/**", "rest/studyprogram/**",
                "rest/swapOffer/**", "rest/term/**", "rest/university/**", "rest/user/**").hasAuthority("ADMIN")
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
        httpSecurity.headers().frameOptions().disable();


    }
}
