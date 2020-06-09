package forest.detector.controller;


import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static forest.detector.templates.HTMLTemplates.*;
import static j2html.TagCreator.*;

@WebServlet(name = "home", urlPatterns = {"/home"})
public class Index extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(Index.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Home page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(300*60);

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
                                                div(BG,TEAM,
                                                        FOOTER).withClass("content-wrapper pb-0")

                                                ).withClass("main-panel")
                                        ).withClass("container-fluid page-body-wrapper")
                                ).withClass("container-scroller")
                        )
                ).withStyle("overflow:hidden");
        response.getWriter().println(homeHtml.render());
    }
}
