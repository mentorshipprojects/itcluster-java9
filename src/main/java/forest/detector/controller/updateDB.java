package forest.detector.controller;

import forest.detector.service.HtmlParser;
import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static forest.detector.utils.AdminTemplates.PARSER_START_BUTTON;
import static forest.detector.utils.HTMLTemplates.*;
import static j2html.TagCreator.*;

@WebServlet(name = "test", urlPatterns = "/admin/db-update")
public class updateDB extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(Parser.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Parser page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag parserButton = html(HEAD,
                body(
                        NAV,
                        div(
                                PARSER_START_BUTTON
                        ).attr("align", "center")
                ),
                FOOTER
        );
        response.getWriter().println(parserButton.render());
    }
}