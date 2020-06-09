package forest.detector.controller;

import forest.detector.dao.entity.Ticket;
import forest.detector.service.TicketService;
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
import static j2html.TagCreator.*;

@WebServlet(name = "admin", urlPatterns = "/admin", loadOnStartup = 1)
public class Admin extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(Admin.class);
    private UserService userService;
    private TicketService ticketService;
//

    /**
     * <script src="https://kit.fontawesome.com/aac0f778d8.js" crossorigin="anonymous"></script>
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Admin page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        if (ticketService == null) {
            ticketService = new TicketService((DataSource) request.getServletContext().getAttribute("datasource"));
        }
        List<Ticket> list = ticketService.getTickets();

        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(300*60);
        String role = (String) session.getAttribute("role");

        if(role == null || !role.equals("admin"))
            response.sendRedirect("/home");
        else if(role.equals("admin")) {

            ContainerTag homeHtml = html(HEAD,
                    body(
                            NAV,

                            div(
                                    div(

                                            MENU(request, (String) session.getAttribute("email"))

                                    ).withId("layoutSidenav_nav"),
                                    div(
                                            main(
                                                    div(
//                                                            h1("Dashboard").withClass("mt-4"),

                                                            div(div(b("Database update: "),div())
                                                                    .withClass("dpm-menu-stats-list-bar dpm-bar2"))
                                                                    .withClass("row").withStyle("justify-content: center"),

                                                            div(
                                                            div(
                                                                    button(i().withClass("fas fa-sync-alt"),text(" Update DB")).withClass("btn btn-success")
                                                                            .withId("update-bd"),
                                                                    button(i().withClass("fas fa-stop-circle"),text(" Stop")).withClass("btn btn-danger")
                                                                    .withId("stop-update-bd")
                                                            ).withClass("row").withStyle("justify-content: center;")
                                                            ).withClass("card mb-4").withStyle("border: none")

                                                    ).withClass("container-fluid")

                                            ),  FOOTER

                                    ).withId("layoutSidenav_content")


                            ).withId("layoutSidenav")

                    ).withClass("sb-nav-fixed")
            );
            response.getWriter().println(homeHtml.render());

        }

    }


}
