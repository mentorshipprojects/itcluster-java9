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
        session.setMaxInactiveInterval(300*60);
        String role = (String) session.getAttribute("role");

        if(role == null || !role.equals("admin"))
            response.sendRedirect("/home");
        else if(role.equals("admin")) {

            ContainerTag homeHtml = html(HEAD,
                    body(
                            NAV(session),

                            div(
                                    div(

                                            MENU(request, (String) session.getAttribute("email"))

                                    ).withId("layoutSidenav_nav"),
                                    div(
                                            main(
                                                    div(
                                                            h1("Dashboard").withClass("mt-4"),
                                                            div(
                                                                    div(
                                                                            div(
                                                                                    div(
                                                                                            i().withClass("fas fa-chart-area mr-1"),
                                                                                            text("Area Chart Example")).withClass("card-header"),
                                                                                    div(
                                                                                            canvas().withId("myAreaChart")
                                                                                                    .attr("width","100%")
                                                                                                    .attr("height","40")

                                                                                    ).withClass("card-body")

                                                                            ).withClass("card mb-4")
                                                                    ).withClass("col-xl-6"),
                                                                    div(
                                                                            div(
                                                                                    div(
                                                                                            i().withClass("fas fa-chart-bar mr-1"),
                                                                                            text("Bar Chart Example")).withClass("card-header"),
                                                                                    div(
                                                                                            canvas().withId("myBarChart")
                                                                                                    .attr("width","100%")
                                                                                                    .attr("height","40")

                                                                                    ).withClass("card-body")

                                                                            ).withClass("card mb-4")
                                                                    ).withClass("col-xl-6")
                                                            ).withClass("row"),
                                                            div(
                                                                    div(i().withClass("fas fa-table mr-1"),text("DataTable Example")
                                                                    ).withClass("card-header"),
                                                                    div(
                                                                            div(

                                                                                    table(
                                                                                            thead(
                                                                                                    tr(
                                                                                                            th("Number"),
                                                                                                            th("Region"),
                                                                                                            th("Forest user"),
                                                                                                            th("Start date"),
                                                                                                            th("Finish date"),
                                                                                                            th("Forestry"),
                                                                                                            th("Cutting type"),
                                                                                                            th("Ticket status"),
                                                                                                            th("Cutting status")
                                                                                                    )

                                                                                            ),
                                                                                            tfoot(
                                                                                                    tr(
                                                                                                            th("Number"),
                                                                                                            th("Region"),
                                                                                                            th("Forest user"),
                                                                                                            th("Start date"),
                                                                                                            th("Finish date"),
                                                                                                            th("Forestry"),
                                                                                                            th("Cutting type"),
                                                                                                            th("Ticket status"),
                                                                                                            th("Cutting status")
                                                                                                    )

                                                                                            ),
                                                                                            tbody(
                                                                                                    each(list, ticket ->
                                                                                                            div(attrs(".ticket"),
                                                                                                                    tr(
                                                                                                                            td(a(ticket.getNumber()).withHref("/tracts?tract="+ticket.getId())),
                                                                                                                            td(ticket.getRegion()),
                                                                                                                            td(ticket.getForestUser()),
                                                                                                                            td(String.valueOf(ticket.getStartDate())),
                                                                                                                            td(String.valueOf(ticket.getFinishDate())),
                                                                                                                            td(ticket.getForestry()),
                                                                                                                            td(ticket.getCuttingType()),
                                                                                                                            td(ticket.getTicketStatus()),
                                                                                                                            td(ticket.getCuttingStatus())
                                                                                                                    )
//


                                                                                                            ))
                                                                                            )


                                                                                    ).withClass("table table-bordered")
                                                                                            .withId("dataTable").attr("width","100%")
                                                                                            .attr("cellspacing","0")

                                                                            ).withClass("table-responsive")

                                                                    ).withClass("card-body")

                                                            ).withClass("card mb-4")

                                                    ).withClass("container-fluid")

                                            ),  FOOTER

                                    ).withId("layoutSidenav_content")


                            ).withId("layoutSidenav")

                    ).withClass("sb-nav-fixed")
            );
            response.getWriter().println(homeHtml.render());

        }

    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        if (userService == null) {
//            userService = new UserService((DataSource) request.getServletContext().getAttribute("datasource"));
//        }
//
//        String email = request.getParameter("email");
//        String role = request.getParameter("role");
//
//        if(email != null && role != null){
//            User user = userService.getUserByEmail(email);
//            if(user != null) {
//                userService.updateUserRoleInDB(role, email);
//                userService.deleteUser(email);
//                response.sendRedirect("/admin");
//            }
//        }
//
//    }
}
