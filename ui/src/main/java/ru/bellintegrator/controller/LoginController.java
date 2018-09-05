package ru.bellintegrator.controller;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.*;

@Controller
public class LoginController {
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @PreAuthorize("permitAll()")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) throws SQLException {
        LOGGER.debug("login get: use database file mode with password encryption - final release for ss");

        Connection conn1 = null;
        Connection conn2 = null;
        try {
            conn1 = DriverManager
                    .getConnection("jdbc:h2:file:.\\data\\data.jar"
                            , "yanesterov", "online");
            Statement statement = conn1.createStatement();
            String sql = "SELECT * FROM BOOKMARK_MANAGER_SCHEMA.BOOKMARKS";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                LOGGER.debug("jdbc result: {" + resultSet.getString("ID")
                        + ", " + resultSet.getString("NAME")
                        + ", " + resultSet.getString("URL")
                        + ", " + resultSet.getString("DESCRIPTION")
                        + ", " + resultSet.getString("VERSION") + "}");
            }

            conn2 = DriverManager
                    .getConnection("jdbc:h2:file:.\\data\\data.jar"
                            , "yanesterov", "online");
            Statement statement1 = conn2.createStatement();
            sql = "SELECT * FROM SECURITY.USERS ";
            ResultSet resultSet1 = statement1.executeQuery(sql);
            while (resultSet1.next()) {
                LOGGER.debug("jdbc result: {" + resultSet1.getString("USERNAME")
                        + ", " + resultSet1.getString("PASSWORD")
                        + ", " + resultSet1.getString("ENABLED") + "}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn1 != null) {
                conn1.close();
            }
            if (conn2 != null) {
                conn2.close();
            }
        }
        return "login";
    }
}
