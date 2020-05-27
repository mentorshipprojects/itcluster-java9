package forest.detector.controller;

import forest.detector.service.HelloService;
import forest.detector.utils.HTMLTemplates;
import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static forest.detector.utils.HTMLTemplates.*;

import static j2html.TagCreator.*;
import static j2html.TagCreator.th;

@WebServlet(name = "home", urlPatterns = {"/home"})
public class Index extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(TemplateController.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Home page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        ContainerTag homeHtml = html(HEAD,
                body(

                        div(
                                iffElse(role == null, NAV, NAV_LOGOUT),
                               // NAV,
                                div(
                                        div(
                                             div(GRAPH,TABLE,FOOTER).withClass("content-wrapper pb-0")

                                        ).withClass("main-panel")
                                ).withClass("container-fluid page-body-wrapper")
                        ).withClass("container-scroller")

                )
        );
        response.getWriter().println(homeHtml.render());
    }
    
}
