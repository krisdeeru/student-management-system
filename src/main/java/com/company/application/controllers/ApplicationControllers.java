package com.company.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationControllers {
    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }
}
