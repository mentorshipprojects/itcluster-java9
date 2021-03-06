package forest.detector.controller;

import forest.detector.dao.entity.User;
import forest.detector.service.UserService;
import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

import static forest.detector.templates.AdminTemplates.*;
import static forest.detector.templates.AdminTemplates.FOOTER;
import static j2html.TagCreator.*;
import static j2html.TagCreator.th;
@WebServlet(name = "users", urlPatterns = "/admin/user-management", loadOnStartup = 1)
public class UserManagement extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(UserManagement.class);
    private UserService userService;

    /**
     * <script src="https://kit.fontawesome.com/aac0f778d8.js" crossorigin="anonymous"></script>
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited User Management page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        if (userService == null) {
            userService = new UserService((DataSource) request.getServletContext().getAttribute("datasource"));
        }

        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(300*60);
        String role = (String) session.getAttribute("role");
        String email = (String) session.getAttribute("email");

        if(role == null || !role.equals("admin"))
            response.sendRedirect("/home");
        else if(role.equals("admin")) {
            List<User> list = userService.getUsers();

            ContainerTag homeHtml = html(
                    title("User Management"),
                    link().withHref("/css/user-management.css").withRel("stylesheet"),
                    HEAD,
                    body(
                            NAV,
                            div(
                                    div(
                                            MENU(request, email)
                                    ).withId("layoutSidenav_nav"),
                                    div(
                                            main(
                                                    div(div(
                                                            div(
                                                                    div(
                                                                            div(
                                                                                    div(
                                                                                            div(
                                                                                                    div(
                                                                                                            h2(text("User "), b("Management"))
                                                                                                    ).withClass("col-sm-5"),
                                                                                                    div(
                                                                                                            a(
                                                                                                                    i("\uE147").withClass("material-icons"),
                                                                                                                    span("Add New User")
                                                                                                            ).withHref("/admin/add_user").withClass("btn btn-primary")
                                                                                                    ).withClass("col-sm-7")

                                                                                            ).withClass("row")
                                                                                    ).withClass("table-title"),
                                                                                    div(
                                                                                            div(
                                                                                                    table(
                                                                                                            thead(
                                                                                                                    tr(
                                                                                                                            th("Email"),
                                                                                                                            th("Password"),
                                                                                                                            th("First name"),
                                                                                                                            th("Last name"),
                                                                                                                            th("Role"),
                                                                                                                            th("Action")
                                                                                                                    )
                                                                                                            ),
                                                                                                            tfoot(
                                                                                                                    th("Email"),
                                                                                                                    th("Password"),
                                                                                                                    th("First name"),
                                                                                                                    th("Last name"),
                                                                                                                    th("Role"),
                                                                                                                    th("Action")

                                                                                                            ),
                                                                                                            tbody(
                                                                                                                    each(list, user ->
                                                                                                                            div(attrs(".user"),
                                                                                                                                    tr(
                                                                                                                                            td(user.getEmail()),
                                                                                                                                            td(user.getPassword()),
                                                                                                                                            td(user.getFirstName()),
                                                                                                                                            td(user.getLastName()),
                                                                                                                                            td(user.getRole()),

                                                                                                                                                    td(
                                                                                                                                                            a(i("\uE8B8").withClass("material-icons"))
                                                                                                                                                                    .withHref("/admin/edit_profil?email="+user.getEmail())
                                                                                                                                                                    .withClass("settings"),
                                                                                                                                                            a(i("\uE5C9").withClass("material-icons"))
                                                                                                                                                                    .withHref("/delete?email="+user.getEmail())
                                                                                                                                                                    .withClass("delete")
                                                                                                                                                    )
                                                                                                                                    )
                                                                                                                            ))
                                                                                                            )
                                                                                                    ).withClass("table table-bordered")
                                                                                                            .withId("dataTable")
                                                                                                            .attr("width", "100%")
                                                                                                            .attr("cellspacing", "0")
                                                                                            ).withClass("table-responsive")
                                                                                    ).withClass("card-body")
                                                                            ).withClass("table-wrapper")
                                                                    ).withClass("container")

                                                            ).withClass("card mb-4")

                                                            ).withClass("container-fluid")
                                                    )

                                            ), FOOTER

                                    ).withId("layoutSidenav_content")

                            ).withId("layoutSidenav")

                    ).withClass("sb-nav-fixed")
            );
            response.getWriter().println(homeHtml.render());
        }
    }
}
