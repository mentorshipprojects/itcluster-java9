package forest.detector.controller;

import forest.detector.dao.entity.Ticket;
import forest.detector.service.TicketService;
import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import static forest.detector.templates.HTMLTemplates.*;

import static j2html.TagCreator.*;
import static j2html.TagCreator.th;

@WebServlet(name = "home", urlPatterns = {"/home"})
public class Index extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(Index.class);
  //  private UserService userService;
    private TicketService ticketService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Home page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        if (ticketService == null) {
            ticketService = new TicketService((DataSource) request.getServletContext().getAttribute("datasource"));
        }
        List<Ticket> list = ticketService.getTickets();

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        ContainerTag homeHtml = html(HEAD,
                body( div(div().withId("loader")).withId("loader-wrapper"),

//                        div(img().withSrc("/img/preloader.gif")).withId("pre-loader-b"),
                        script(rawHtml("$('document').ready(function() {\n" +
                                "$(\"#loader-wrapper\").css(\"display\",\"none\")\n" +
                                "        $(\"html\").css(\"overflow\",\"auto\")\n" +
                                "        \n" +
                                "\n" +
                                "    });")),
                        div(
                                iffElse(role == null, NAV,iffElse(role == "admin",  NAV_LOGOUT,ADM_NAV)),



                                div(
                                        div(
                                                div(GRAPH,div(
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
                                                                                                ))
                                                                                )).withClass("table table-bordered")
                                                                                        .withId("dataTable").attr("width","100%")
                                                                                        .attr("cellspacing","0")

                                                                        ).withClass("table-responsive")

                                                                ).withClass("card-body")

                                                        ).withClass("card mb-4"),FOOTER).withClass("content-wrapper pb-0")

                                                ).withClass("main-panel")
                                        ).withClass("container-fluid page-body-wrapper")
                                ).withClass("container-scroller")
                        )
                ).withStyle("overflow:hidden");
        response.getWriter().println(homeHtml.render());
    }
}
