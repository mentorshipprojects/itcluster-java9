package forest.detector.controller;

import forest.detector.dao.entity.Tract;
import forest.detector.dao.repository.TractRepository;
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
import java.io.PrintWriter;
import java.util.List;

import static forest.detector.templates.HTMLTemplates.*;
import static j2html.TagCreator.*;
import static j2html.TagCreator.td;

@WebServlet(name = "tracts", urlPatterns = {"/tracts"})
public class Tracts extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(Index.class);
    private TractRepository tractRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info("Visited tracts page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        tractRepository = new TractRepository((DataSource) request.getServletContext().getAttribute("datasource"));
        PrintWriter out = response.getWriter();
        int ticketID;
        if (request.getParameter("ticket-id") == null) {
            response.sendRedirect("/table");
        } else {
            ticketID = Integer.parseInt(request.getParameter("ticket-id"));
            List <Tract> list = tractRepository.getTracts(ticketID);
//            String html = "";
//            for (int i = 0; i < list.size(); i++) {
//                html += "<td>" + list.get(i).getQuarter() + "</td>";
//                html += "<td>" + list.get(i).getDivision() + "</td>";
//                html += "<td>" + list.get(i).getRange() + "</td>";
//                html += "<td>" + list.get(i).getArea() + "</td>";
//                html += "<td>" + list.get(i).getForestType() + "</td>";
//                html += "<td>" + list.get(i).getGeneralAllowedExtent() + "</td>";
//                html += "<td>" + list.get(i).getContributor() + "</td>";
//            }



            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(300*60);
            ContainerTag homeHtml = html(
                    title("Лісорубні ділянки"),
                    HEAD,
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
                                                                    div(i().withClass("fas fa-table mr-1"),text("Лісорубні ділянки")
                                                                    ).withClass("card-header"),
                                                                    div(
                                                                            div(

                                                                                    table(
                                                                                            thead(
                                                                                                    tr(
                                                                                                            th("Квартал"),
                                                                                                            th("Виділ"),
                                                                                                            th("Ділянка"),
                                                                                                            th("Площа"),
                                                                                                            th("Господарство"),
                                                                                                            th(text("Запас деревини,"), br() ,text(" дозволений до заготівлі")),
                                                                                                            th("Виконавець рубки")
                                                                                                    )
                                                                                            ),
//                                                                                            tfoot(
//                                                                                                    tr(
//                                                                                                            th("Квартал"),
//                                                                                                            th("Виділ"),
//                                                                                                            th("Ділянка"),
//                                                                                                            th("Площа"),
//                                                                                                            th("Господарство"),
//                                                                                                            th("Запас деревини, дозволений до заготівлі"),
//                                                                                                            th("Виконавець рубки")
//                                                                                                    )
//                                                                                            ),
                                                                                            tbody(
                                                                                                    each(list, tract ->
                                                                                                            div(attrs(".ticket"),
                                                                                                                    tr(
                                                                                                                            td(tract.getQuarter()).withClass("table-td-c"),
                                                                                                                            td(tract.getDivision()).withClass("table-td-c"),
                                                                                                                            td(tract.getRange()).withClass("table-td-c"),
                                                                                                                            td(String.valueOf(tract.getArea())+" Га").withClass("table-td-c"),
                                                                                                                            td(tract.getForestType()).withClass("table-td-c"),
                                                                                                                            td(String.valueOf(tract.getGeneralAllowedExtent())+" М³").withClass("table-td-c"),
                                                                                                                            td(tract.getContributor()).withClass("table-td-c")





                                                                                                                    )
                                                                                                            ))
                                                                                            )).withClass("table table-bordered")
                                                                                            .withId("dataTablee").attr("width","100%")
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
}
