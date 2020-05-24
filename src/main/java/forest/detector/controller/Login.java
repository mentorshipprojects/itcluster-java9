package forest.detector.controller;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static forest.detector.utils.AdminTemplates.*;
import static j2html.TagCreator.*;
import static j2html.TagCreator.div;
@WebServlet(name = "login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(TemplateController.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Login page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(
                title("Login"),
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
                                                                h3("Login").withClass("text-center font-weight-light my-4")
                                                        ).withClass("card-header"),
                                                        div(
                                                                form(
                                                                      div(
                                                                             label("Email").withClass("small mb-1")
                                                                              .attr("for","inputEmailAddress"),
                                                                              input().withClass("form-control py-4")
                                                                              .withId("inputEmailAddress")
                                                                                      .withName("email")

                                                                              .withPlaceholder("Enter email address")
                                                                              .withType("email")
                                                                      ).withClass("form-group"),

                                                                        div(
                                                                                label("Password").withClass("small mb-1")
                                                                                        .attr("for","inputPassword"),
                                                                                input().withClass("form-control py-4")
                                                                                        .withId("inputPassword")
                                                                                        .withName("password")

                                                                                        .withPlaceholder("Enter password")
                                                                                        .withType("password")
                                                                        ).withClass("form-group"),
                                                                        div(
//                                                                             a("Forgot Password?").withClass("small")
//                                                                                .withHref("")
                                                                               button("Login").withClass("btn btn-primary btn-block").withType("submit")

                                                                        ).withClass("form-group d-flex align-items-center justify-content-between mt-4 mb-0 center-a")
                                                                )
                                                        ).withClass("card-body"),
                                                                div(
                                                                      div(
                                                                              a("Need an account? Sign up!").withHref("/register")
                                                                      ).withClass("small")
                                                                ).withClass("card-footer text-center")
                                                ).withClass("card shadow-lg border-0 rounded-lg mt-5")
                                        ).withClass("col-lg-5")
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
