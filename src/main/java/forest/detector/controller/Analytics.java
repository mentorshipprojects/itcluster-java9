package forest.detector.controller;

import forest.detector.dao.entity.Stat;
import forest.detector.dao.entity.Ticket;
import forest.detector.dao.repository.AnalyticsRepository;
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
@WebServlet(name = "analytics", urlPatterns = {"/analytics"})
public class Analytics extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(Index.class);
    private TicketService ticketService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AnalyticsRepository ar = new AnalyticsRepository((DataSource) request.getServletContext().getAttribute("datasource"));
        List<Stat>  statCuttingType = ar.statCuttingType(2020);
        Stat first = statCuttingType.get(0);
        Stat two = statCuttingType.get(1);
        log.info("Visited analytics page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        if (ticketService == null) {
            ticketService = new TicketService((DataSource) request.getServletContext().getAttribute("datasource"));
        }
//        List<Ticket> list = ticketService.getTickets();

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(300*60);
//        String role = (String) session.getAttribute("role");

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
                                                                div(
                                                                        i().withClass("fas fa-chart-area mr-1"),
                                                                        text("Area Chart Example")
                                                                ).withClass("card-header"),
                                                                div(
                                                                    label(first.getStatName()).withClass("pg-h-label"),br(),
                                                                        div(

                                                                        progress()
                                                                                .withValue(String.valueOf(first.getGeneralAllowedExtent()))
                                                                                .attr("max","500000")
                                                                                .withClass("pg-bar-0"),
                                                                        label(String.valueOf(first.getGeneralAllowedExtent())+" Га").withClass("pg-lable"),
                                                                        progress()
                                                                                .withValue(String.valueOf(two.getGeneralAllowedExtent()))
                                                                                .attr("max","500000")
                                                                                .withClass("pg-bar-1"),label(String.valueOf(two.getGeneralAllowedExtent())+" М³").withClass("pg-lable")
                                                                        ).withClass("raw").withStyle("display: inline-block;width: 100%")

                                                                ).withClass("card-body")

                                                        ).withClass("card mb-4"),



                                                       GRAPH
                                                        ,FOOTER).withClass("content-wrapper pb-0")
,script(rawHtml("// Set new default font family and font color to mimic Bootstrap's default styling\n" +
                                                        "var chart = new Chart('myAreaChart', {\n" +
                                                        "  type: 'horizontalBar',\n" +
                                                        "  data: {\n" +
                                                        "    labels: ['A', 'B', 'C'],\n" +
                                                        "    datasets: [\n" +
                                                        "      {\n" +
                                                        "        data: [10, 20, 30],\n" +
                                                        "        backgroundColor: '#af90ca',\n" +
                                                        "        label: 'Before'\n" +
                                                        "      },\n" +
                                                        "      {\n" +
                                                        "        data: [50, 30, 40],\n" +
                                                        "        backgroundColor: '#c46998',\n" +
                                                        "        label: 'After'\n" +
                                                        "      }\n" +
                                                        "    ]\n" +
                                                        "  },\n" +
                                                        "  options: {\n" +
                                                        "    scales: {\n" +
                                                        "      xAxes: [{\n" +
                                                        "        ticks: {\n" +
                                                        "          beginAtZero: true\n" +
                                                        "        }\n" +
                                                        "      }]\n" +
                                                        "    }\n" +
                                                        "  }\n" +
                                                        "});"))
                                        ).withClass("main-panel")
                                ).withClass("container-fluid page-body-wrapper")
                        ).withClass("container-scroller")
                )
        ).withStyle("overflow:hidden");
        response.getWriter().println(homeHtml.render());
    }
}
