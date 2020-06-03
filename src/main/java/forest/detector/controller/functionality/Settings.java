package forest.detector.controller.functionality;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static forest.detector.utils.AdminTemplates.HEAD;
import static forest.detector.utils.EditProfileTemplates.EDIT_PROF_FORM;
import static j2html.TagCreator.*;
import static j2html.TagCreator.script;
@WebServlet(name = "settings", urlPatterns = {"/settings"})
public class Settings extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(Settings.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Settings page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(
                title("Settings"),
                HEAD,
                body(
                        div(
                                div(
                                        main(
                                                div(
                                                        div(
                                                                div(
                                                                        div(
                                                                                div(
                                                                                        h3("Settings").withClass("text-center font-weight-light my-4")
                                                                                ).withClass("card-header"),
                                                                                div(
                                                                                        EDIT_PROF_FORM
                                                                                ).withClass("card-body"),
                                                                                div(
                                                                                        div(
                                                                                                a("Need an account? Sign up!").withHref("/register")
                                                                                        ).withClass("small")
                                                                                ).withClass("card-footer text-center")
                                                                        ).withClass("card shadow-lg border-0 rounded-lg mt-5")
                                                                ).withClass("col-lg-7")
                                                        ).withClass("row justify-content-center")
                                                ).withClass("container")
                                        )
                                ).withId("layoutAuthentication_content")
                        ).withId("layoutAuthentication"),
                        script().withSrc("https://code.jquery.com/jquery-3.4.1.min.js")
                                .attr("crossorigin","anonymous"),
                        script().withSrc("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js")
                                .attr("crossorigin","anonymous")
                ).withClass("bg-primary")
        );
        response.getWriter().println(homeHtml.render());
    }
}
