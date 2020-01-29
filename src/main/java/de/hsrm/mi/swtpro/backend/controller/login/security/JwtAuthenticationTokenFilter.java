package de.hsrm.mi.swtpro.backend.controller.login.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The component JwtAuthenticationTokenFilter is a servletFilter which extract
 * the token from a http request.
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${security.jwt.token.header:Authorization}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestHeader = request.getHeader(this.tokenHeader);
      
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String authenticationToken = requestHeader.substring(7);
            JwtAuthentication authentication = new JwtAuthentication(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    public String getTokenHeader() {
        return tokenHeader;
    }
}