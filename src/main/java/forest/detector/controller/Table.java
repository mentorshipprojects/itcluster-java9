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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static forest.detector.templates.HTMLTemplates.*;
import static forest.detector.templates.HTMLTemplates.FOOTER;
import static j2html.TagCreator.*;
import static j2html.TagCreator.td;
@WebServlet(name = "table", urlPatterns = {"/table"})
public class Table extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(Index.class);
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
        Set<String> forestUser = new TreeSet<>();
        Set<String> forestry = new TreeSet<>();
        Set<String> start_date = new TreeSet<>();
        Set<String> finish_date = new TreeSet<>();
        Set<String> cutting_type = new TreeSet<>();

        for(int i= 0; i<list.size();i++){

            forestUser.add(list.get(i).getForestUser());
            forestry.add(list.get(i).getForestry());
            start_date.add(String.valueOf(list.get(i).getStartDate()));
            finish_date.add(String.valueOf(list.get(i).getFinishDate()));
            cutting_type.add(list.get(i).getCuttingType());
        }
        Iterator forestUserIterator = forestUser.iterator();
        Iterator forestUserIterator2 = forestUser.iterator();
        Iterator forestryIterator = forestry.iterator();
        Iterator forestryIterator2 = forestry.iterator();
        Iterator start_dateIterator = start_date.iterator();
        Iterator start_dateIterator2 = start_date.iterator();
        Iterator finish_dateIterator = finish_date.iterator();
        Iterator finish_dateIterator2 = finish_date.iterator();
        Iterator cuttingTypeIterator = cutting_type.iterator();
        Iterator cuttingTypeIterator2 = cutting_type.iterator();

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(300*60);

        ContainerTag homeHtml = html(HEAD,
                body( div(div().withId("loader")).withId("loader-wrapper"),
                        script(rawHtml("$('document').ready(function() {\n" +
                                "$(\"#loader-wrapper\").css(\"display\",\"none\")\n" +
                                "        $(\"html\").css(\"overflow\",\"auto\")\n" +
                                "        \n" +
                                "\n" +
                                "    });")),
                        div(
                                NAV(session),
                                div(
                                        div(
                                                div(
                                                        div(
                                                                div(i().withClass("fas fa-filter"),text(" Filter")).withClass("card-header"),
                                                                div(

                                                                        form().withAction("/table").withMethod("post").with(
                                                                                select(
                                                                                        option("Forest user").attr("selected"),
                                                                                        option( each(forestUser, ticket ->
                                                                                                div(attrs(".ticket"),
                                                                                                        tr(

                                                                                                                option((String) forestUserIterator.next())
                                                                                                        )))).withValue(
                                                                                                String.valueOf(each(forestUser, ticket ->
                                                                                                        div(attrs(".ticket"),

                                                                                                                option((String)forestUserIterator2.next())
                                                                                                        ))))
                                                                                ).withClass("browser-default custom-select").withName("forest_user").withId("forest_user"),

                                                                                select(
                                                                                        option("Forestry").attr("selected"),
                                                                                        option(each(forestry, ticket ->
                                                                                                div(attrs(".ticket"),
                                                                                                        tr(
                                                                                                                option((String) forestryIterator.next())
                                                                                                        )))).withValue(
                                                                                                String.valueOf(each(forestry, ticket ->
                                                                                                        div(attrs(".ticket"),
                                                                                                                option((String) forestryIterator2.next())))))
                                                                                ).withClass("browser-default custom-select").withName("forestry"),

                                                                                 select(
                                                                                        option("Start date").attr("selected"),
                                                                                         option(each(start_date, ticket ->
                                                                                                 div(attrs(".ticket"),
                                                                                                         tr(
                                                                                                                 option((String) start_dateIterator.next())
                                                                                                         )))).withValue(
                                                                                                 String.valueOf(each(start_date, ticket ->
                                                                                                         div(attrs(".ticket"),
                                                                                                                 tr(
                                                                                                                         option((String) start_dateIterator2.next())
                                                                                                                 )))))
                                                                                 ).withClass("browser-default custom-select").withName("start_date"),

                                                                                select(
                                                                                        option("Finish date").attr("selected"),
                                                                                        option(each(finish_date, ticket ->
                                                                                                div(attrs(".ticket"),
                                                                                                        tr(
                                                                                                                option((String) finish_dateIterator.next())
                                                                                                        )))).withValue(
                                                                                                String.valueOf(each(finish_date, ticket ->
                                                                                                        div(attrs(".ticket"),
                                                                                                                tr(
                                                                                                                        option((String) finish_dateIterator2.next())
                                                                                                                ))))
                                                                                        )
                                                                                ).withClass("browser-default custom-select").withName("finish_date"),

                                                                                select(
                                                                                        option("Cutting type").attr("selected"),
                                                                                        option(each(cutting_type, ticket ->
                                                                                                div(attrs(".ticket"),
                                                                                                        tr(
                                                                                                                option((String) cuttingTypeIterator.next())
                                                                                                        )))).withValue(
                                                                                                String.valueOf(each(cutting_type, ticket ->
                                                                                                        div(attrs(".ticket"),
                                                                                                                tr(
                                                                                                                        option((String) cuttingTypeIterator2.next())
                                                                                                                ))))
                                                                                        )
                                                                                ).withClass("browser-default custom-select").withName("cutting_type"),

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
