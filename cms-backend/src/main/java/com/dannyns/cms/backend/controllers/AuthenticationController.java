package com.dannyns.cms.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.dannyns.cms.backend.CONST.LOGIN_PAGE;

@Controller
public class AuthenticationController {

    @GetMapping(LOGIN_PAGE)
    public String login() {
        return "authentication/login";
    }
}
