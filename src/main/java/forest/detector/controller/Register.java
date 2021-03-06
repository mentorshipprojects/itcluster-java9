package forest.detector.controller;

import forest.detector.service.UserService;
import forest.detector.utils.PasswordHashing;
import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

import static forest.detector.templates.AdminTemplates.HEAD;
import static j2html.TagCreator.*;
import static j2html.TagCreator.script;
@WebServlet(name = "register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(Register.class);
    private UserService userService;
    private PasswordHashing hashing = new PasswordHashing();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Register page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(
                title("Register"),
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
                                                                                        h3("Create Account").withClass("text-center font-weight-light my-4")
                                                                                ).withClass("card-header"),
                                                                                div(
                                                                                        form().withMethod("post").with(
                                                                                                div(
                                                                                                        label("First name").withClass("small mb-1")
                                                                                                                .attr("for","firstName"),
                                                                                                        input().withClass("form-control py-4")
                                                                                                                .withId("firstName")
                                                                                                                .withName("firstName")

                                                                                                                .withPlaceholder("Enter frist name")
                                                                                                                .withType("text")
                                                                                                ).withClass("form-group"),

                                                                                                div(
                                                                                                        label("Last name").withClass("small mb-1")
                                                                                                                .attr("for","lastName"),
                                                                                                        input().withClass("form-control py-4")
                                                                                                                .withId("lastName")
                                                                                                                .withName("lastName")
                                                                                                                .withPlaceholder("Enter last name")
                                                                                                                .withType("text")
                                                                                                ).withClass("form-group"),

                                                                                                div(
                                                                                                        label("Email").withClass("small mb-1")
                                                                                                                .attr("for","inputEmailAddress"),
                                                                                                        input().withClass("form-control py-4")
                                                                                                                .withId("inputEmailAddress")
                                                                                                                .withName("email")
                                                                                                                .attr("aria-describedby","emailHelp")
                                                                                                                .withPlaceholder("Enter email address")
                                                                                                                .withType("email")
                                                                                                ).withClass("form-group"),

                                                                                                div(  div( div(
                                                                                                        label("Password").withClass("small mb-1")
                                                                                                                .attr("for","inputPassword"),
                                                                                                        input().withClass("form-control py-4")
                                                                                                                .withId("inputPassword")
                                                                                                                .withName("password")
                                                                                                                .withPlaceholder("Enter password")
                                                                                                                .withType("password")
                                                                                                        ).withClass("form-group")).withClass("col-md-6"),

                                                                                                        div( div(
                                                                                                                label("Confirm Password").withClass("small mb-1")
                                                                                                                        .attr("for","inputConfirmPassword"),
                                                                                                                input().withClass("form-control py-4")
                                                                                                                        .withId("inputConfirmPassword")
                                                                                                                        .withPlaceholder("Confirm password")
                                                                                                                        .withType("password")
                                                                                                        ).withClass("form-group")).withClass("col-md-6")


                                                                                                ).withClass("form-row"),


                                                                                                div(
//                                                                             a("Forgot Password?").withClass("small")
//                                                                                .withHref("")
                                                                                                        button("Create Account").withClass("btn btn-primary btn-block").withType("submit")
                                                                                                        //.attr("disabled","disabled")
                                                                                                ).withClass("form-group d-flex align-items-center justify-content-between mt-4 mb-0 center-a")
                                                                                        )
                                                                                ).withClass("card-body"),
                                                                                div(
                                                                                        div(
                                                                                                a("Have an account? Go to login").withHref("/login")
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
                        script().withSrc("/js/passConfrim.js"),
                        script().withSrc("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js")
                                .attr("crossorigin","anonymous")
                ).withClass("bg-primary")
        );
        response.getWriter().println(homeHtml.render());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = hashing.getHash(request.getParameter("password"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String avatar = request.getParameter("avatar");

        if (userService == null) {
            userService = new UserService((DataSource) request.getServletContext().getAttribute("datasource"));
        }

        if(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9+])*(\\.[A-Za-z]{2,})$")){

            userService.setUserInDB(email,password,firstName,lastName, avatar);
            response.sendRedirect(request.getContextPath()+ "/login");
        }
    }
}
