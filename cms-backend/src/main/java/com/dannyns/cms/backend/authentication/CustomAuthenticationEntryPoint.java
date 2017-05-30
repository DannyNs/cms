package com.dannyns.cms.backend.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import static com.dannyns.cms.backend.CONST.*;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authError) throws IOException, ServletException {
        String header = request.getHeader(HttpHeaders.ACCEPT);
        boolean restRequest = header.contains(MediaType.APPLICATION_JSON);
        String requestURI = request.getRequestURI();
        boolean restLoginRequest = requestURI.startsWith(REST_AUTHENTICATION_URI);

        HttpSession session = request.getSession(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = (authentication == null) ? null : authentication.getName();

        if (session == null || name == null) {
            if (restRequest) {
                if (!restLoginRequest) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                response.sendRedirect(LOGIN_PAGE);
            }
        } else {
            if (restRequest) {
                if (!restLoginRequest) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                response.sendRedirect(ERROR_PAGE);
            }
        }
    }

}
