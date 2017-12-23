package ru.bellintegrator.bookmark_manager.controller;

import org.apache.log4j.Logger;
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

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model){
        try{
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/empDS");
            Connection conn = null;
            try {
                conn = ds.getConnection();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * from categories");
                while (resultSet.next()){
                    LOGGER.trace(resultSet.getInt("ID"));
                }
            } finally {
                if (conn!=null) { conn.close(); }
            }
        }catch (Exception e){

        }
        model.addAttribute("msg", "Hello Spring!");
        return "test";
    }
}
