package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/empDS");
            Connection conn = null;
            try {
                conn = ds.getConnection();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM bookmark_manager_schema.categories");
                while (resultSet.next()) {
                    LOGGER.debug("================Data from DB: " + resultSet.getInt("ID"));
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception e) {
            LOGGER.debug("=================Exception: ", e);
        }
        model.addAttribute("msg", "Hello Spring!");

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
        return "test";
    }
}
