package de.hsrm.mi.swtpro.backend.controller.login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import de.hsrm.mi.swtpro.backend.controller.login.config.JwtUserDetailsService;
import de.hsrm.mi.swtpro.backend.model.User;
import de.hsrm.mi.swtpro.backend.model.UserRights;
import de.hsrm.mi.swtpro.backend.service.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * The component JwtAuthenticationTokenFilter is a servletFilter which extract
 * the token from a http request.
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${security.jwt.token.header:Authorization}")
    private String tokenHeader;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestHeader = request.getHeader(this.tokenHeader);
        System.out.println("War hier JwtAuthenticationTokenFilter " + requestHeader);

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String authenticationToken = requestHeader.substring(7);
            String loginname = tokenService.getUsernameFromToken(authenticationToken);
            Optional<User> optionalUser = userRepository.findByLoginName(loginname);
            if (!optionalUser.isPresent()) {
                throw new UsernameNotFoundException(loginname);
            }
            User user = optionalUser.get();
         
            UserRights userRights = user.getUserRights();
            if(userRights != null){
                String userRight = userRights.getUserRights();
                System.out.println("USER Rights" + userRight);

            }
           

           // org.springframework.security.core.userdetails.User.UserBuilder builder = null;
            //if (user != null) {
            
            UserDetails detailUser = jwtUserDetailsService.loadUserByUsername(loginname);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(detailUser, null, Arrays.asList(new SimpleGrantedAuthority("USER")));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            System.out.println("JwtAuthenticationTokenFilter loginname " + loginname + " " + " " + detailUser + " " + authenticationToken) ;
            System.out.println("JwtAuthenticationTokenFilter authentication " + authentication.toString()) ;
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    public String getTokenHeader() {
        return tokenHeader;
    }
}