package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
@RequestMapping("/security")
public class SecurityController {
    private static final Logger LOGGER = Logger.getLogger(SecurityController.class);

    public SecurityController() {
    }

    @GetMapping("/loginfail")
    public String loginFail(Model model, Locale locale){
        LOGGER.info("Login failed detected");
//        TODO: implement message source book 694
        model.addAttribute("error", "error");
        return "categories/list";
    }
}
