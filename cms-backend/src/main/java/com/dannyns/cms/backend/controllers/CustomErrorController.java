package com.dannyns.cms.backend.controllers;

import com.dannyns.cms.backend.annotations.Layout;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.dannyns.cms.backend.CONST.ERROR_PAGE;

@Controller
@Layout("server-error")
public class CustomErrorController implements ErrorController {

    @GetMapping(ERROR_PAGE)
    public String renderErrorPage() {
        return "error/error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PAGE;
    }
}
