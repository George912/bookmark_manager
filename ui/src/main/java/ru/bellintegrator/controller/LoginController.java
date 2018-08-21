package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bellintegrator.core.domain.User;

import javax.validation.Valid;

@Controller
public class LoginController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        LOGGER.debug("login get");
        return "login";

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(Model model) {
        LOGGER.debug("login post");
        return "categories/list";
    }
}
