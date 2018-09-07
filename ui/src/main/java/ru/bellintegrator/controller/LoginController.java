package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
public class LoginController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) throws SQLException {
        LOGGER.debug("login get");
        return "login";
    }
}