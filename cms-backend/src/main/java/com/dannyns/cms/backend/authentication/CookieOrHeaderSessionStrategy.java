package com.dannyns.cms.backend.authentication;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.session.Session;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.MultiHttpSessionStrategy;

import static com.dannyns.cms.backend.CONST.REST_URI_PREFIX;

public class CookieOrHeaderSessionStrategy implements MultiHttpSessionStrategy {

    private Map<String, SessionType> sessionType = new HashMap<>();
    private HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();
    private CookieHttpSessionStrategy cookieHttpSessionStrategy = new CookieHttpSessionStrategy();

    @Override
    public String getRequestedSessionId(HttpServletRequest request) {
        String sessionId;
        boolean restRequest = request.getRequestURI().startsWith(REST_URI_PREFIX);

        if (restRequest) {
            sessionId = headerHttpSessionStrategy.getRequestedSessionId(request);

        } else {
            sessionId = cookieHttpSessionStrategy.getRequestedSessionId(request);
        }

        return sessionId;
    }

    @Override
    public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {
        boolean restRequest = request.getRequestURI().startsWith(REST_URI_PREFIX);

        if (restRequest) {
            headerHttpSessionStrategy.onNewSession(session, request, response);
        } else {
            cookieHttpSessionStrategy.onNewSession(session, request, response);
        }

        sessionType.put(session.getId(), (restRequest) ? SessionType.HEADER : SessionType.COOKIE);
    }

    @Override
    public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getSession().getId();

        if (sessionType.get(sessionId) == SessionType.HEADER) {
            headerHttpSessionStrategy.onInvalidateSession(request, response);
        } else {
            cookieHttpSessionStrategy.onInvalidateSession(request, response);
        }

        sessionType.remove(sessionId);
    }

    @Override
    public HttpServletRequest wrapRequest(HttpServletRequest request, HttpServletResponse response) {
        return request;
    }

    @Override
    public HttpServletResponse wrapResponse(HttpServletRequest request, HttpServletResponse response) {
        return response;
    }

    private static enum SessionType {

        HEADER, COOKIE
    }
}
