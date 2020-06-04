package forest.detector.controller;

import forest.detector.dao.entity.Ticket;
import forest.detector.service.TicketService;
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

import static forest.detector.templates.HTMLTemplates.*;
import static forest.detector.templates.HTMLTemplates.FOOTER;
import static j2html.TagCreator.*;
import static j2html.TagCreator.td;
@WebServlet(name = "table", urlPatterns = {"/table"})
public class Table extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(Index.class);
    //  private UserService userService;
    private TicketService ticketService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited table page!");
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
//                                iffElse(role == null, NAV,iffElse(role == "admin",  NAV_LOGOUT,ADM_NAV)),
                                NAV(session),

                                div(
                                        div(
                                                div(
                                                        div(
                                                                div(i().withClass("fas fa-filter"),text(" Filter")).withClass("card-header"),
                                                                div(

                                                                        form(
                                                                                select(
                                                                                        option("Forest user").attr("selected"),
                                                                                        option("1").withValue("1"),
                                                                                        option("2").withValue("2"),
                                                                                        option("2").withValue("2")
                                                                                ).withClass("browser-default custom-select"),
                                                                                select(
                                                                                        option("Forestry").attr("selected"),
                                                                                        option("1").withValue("1"),
                                                                                        option("2").withValue("2"),
                                                                                        option("2").withValue("2")
                                                                                ).withClass("browser-default custom-select")
                                                                                , select(
                                                                                        option("Start date").attr("selected"),
                                                                                        option("1").withValue("1"),
                                                                                        option("2").withValue("2"),
                                                                                        option("2").withValue("2")
                                                                                ).withClass("browser-default custom-select"),
                                                                                select(
                                                                                        option("Finish date").attr("selected"),
                                                                                        option("1").withValue("1"),
                                                                                        option("2").withValue("2"),
                                                                                        option("2").withValue("2")
                                                                                ).withClass("browser-default custom-select"),
                                                                                select(
                                                                                        option("Cutting type").attr("selected"),
                                                                                        option("1").withValue("1"),
                                                                                        option("2").withValue("2"),
                                                                                        option("2").withValue("2")
                                                                                ).withClass("browser-default custom-select"),
                                                                                button("Search").withClass("btn btn-primary")
                                                                                        .withType("submit")

                                                                        ).withClass("filter-form")
                                                                ).withClass("card-body")
                                                        ).withClass("card mb-4"),




                                                        div(
















                                                        div(i().withClass("fas fa-table mr-1"),text("DataTable Example")
                                                        ).withClass("card-header"),
                                                        div(
                                                                div(

                                                                        table(
                                                                                thead(
                                                                                        tr(
                                                                                                th("Number"),

                                                                                                th("Forest user"),
                                                                                                th("Start date"),
                                                                                                th("Finish date"),
                                                                                                th("Forestry"),
                                                                                                th("Cutting type")
                                                                                        )
                                                                                ),
                                                                                tfoot(
                                                                                        tr(
                                                                                                th("Number"),

                                                                                                th("Forest user"),
                                                                                                th("Start date"),
                                                                                                th("Finish date"),
                                                                                                th("Forestry"),
                                                                                                th("Cutting type")

                                                                                        )
                                                                                ),
                                                                                tbody(
                                                                                        each(list, ticket ->
                                                                                                div(attrs(".ticket"),
                                                                                                        tr(
                                                                                                                td(a(ticket.getNumber()).withHref("/tracts?tract="+ticket.getId())),

                                                                                                                td(ticket.getForestUser()),
                                                                                                                td(String.valueOf(ticket.getStartDate())),
                                                                                                                td(String.valueOf(ticket.getFinishDate())),
                                                                                                                td(ticket.getForestry()),
                                                                                                                td(ticket.getCuttingType())

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
