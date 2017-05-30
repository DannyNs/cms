package com.dannyns.cms.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.dannyns.cms.backend.CONST.DEFAULT_PAGE;

@Controller
public class IndexController {

    @GetMapping(DEFAULT_PAGE)
    public String getIndex() {
        return "index/index";
    }
}
