package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bellintegrator.db.exception.ServiceException;
import ru.bellintegrator.db.model.Category;
import ru.bellintegrator.db.service.CategoryService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@RequestMapping("/test")
@Controller
public class TestController {
    private static final Logger LOGGER = Logger.getLogger(TestController.class);
    private CategoryService categoryService;

    public TestController() {
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        try {
            if (categoryService != null) {
                List<Category> categories = categoryService.list();
                for (Category category : categories) {
                    LOGGER.debug("===========Data: " + category);
                }
            } else {
                LOGGER.debug("==========================categoryService is null");
            }

        } catch (ServiceException e) {
            LOGGER.debug("Exception while receiving category list: ", e);
        }
        return "categories/list";
    }
}
