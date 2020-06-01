package forest.detector.controller;

import forest.detector.dao.entity.User;
import forest.detector.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "edit_profile", urlPatterns = "/delete")
public class DeleteUser extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(DeleteUser.class);
    private UserService userService;
    private User user;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (userService == null) {
            userService = new UserService((DataSource) request.getServletContext().getAttribute("datasource"));
        }

        user = userService.getUserByEmail(request.getParameter("email"));
        userService.deleteUser(user.getEmail());
        response.sendRedirect("/admin/user-management");
    }
}
