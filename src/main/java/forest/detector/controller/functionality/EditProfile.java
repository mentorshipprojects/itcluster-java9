package forest.detector.controller.functionality;

import forest.detector.dao.entity.User;
import forest.detector.service.UserService;
import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

import static forest.detector.templates.AdminTemplates.*;
import static forest.detector.templates.AdminTemplates.FOOTER;
import static j2html.TagCreator.*;

@WebServlet(name = "edit_profil", urlPatterns = "/admin/edit_profil")
public class EditProfile extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(EditProfile.class);
    private UserService userService;
    private User user;

    /**
     * <script src="https://kit.fontawesome.com/aac0f778d8.js" crossorigin="anonymous"></script>
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if(role == null)
            response.sendRedirect("/home");
        else if(role.equals("user") || role.equals("moderator-api") || role.equals("moderator-gui"))
            response.sendRedirect("/home");
        else if(role.equals("admin")){

            log.info("Visited Edit Profile page!");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            if (userService == null) {
                userService = new UserService((DataSource) request.getServletContext().getAttribute("datasource"));
            }

            user = userService.getUserByEmail(request.getParameter("email"));

            ContainerTag homeHtml = html(
                    title("Edit Profile "),
                    link().withHref("/css/user-management.css").withRel("stylesheet"),
                    HEAD,
                    body(
                            NAV(session),
                            div(
                                    div(
                                            MENU(request, (String) session.getAttribute("email"))

                                    ).withId("layoutSidenav_nav"),
                                    div(
                                            main(
                                                    div(
                                                            div(
                                                                    div(
                                                                            div(div(
                                                                                    div(
                                                                                            div(
                                                                                                    div(
                                                                                                            h2(text("Edit "),b("Profile  "),b(request.getParameter("email"))
                                                                                                            ).withClass("col-sm-5"),
                                                                                                            div(

                                                                                                            ).withClass("col-sm-7")

                                                                                                    ).withClass("row")
                                                                                            ).withClass("table-title"),

                                                                                            div(
                                                                                                    div(

                                                                                                            div(

                                                                                                                    form().withMethod("post").with(
                                                                                                                            div(
                                                                                                                                    label("Role").attr("for","nickname")
                                                                                                                                            .withClass("col-4 col-form-label"),
                                                                                                                                    div(
                                                                                                                                            select(
                                                                                                                                                    option("Admin").withValue("admin"),
                                                                                                                                                    option("Moderator-api").withValue("moderator-api"),
                                                                                                                                                    option("Moderator-gui").withValue("moderator-gui"),
                                                                                                                                                    option("User").withValue("user")

                                                                                                                                            ).withId("role")
                                                                                                                                                    .withName("role")
                                                                                                                                                    .withClass("custom-select")
                                                                                                                                                    .withType("text")
                                                                                                                                                    .withValue(user.getRole())
                                                                                                                                    ).withClass("col-8")
                                                                                                                            ).withClass("form-group row"),

                                                                                                                            div(
                                                                                                                                    label("Avatar").attr("for","avatar")
                                                                                                                                            .withClass("col-4 col-form-label"),
                                                                                                                                    div(
                                                                                                                                            input().withId("avatar")
                                                                                                                                                    .withName("avatar")
                                                                                                                                                    .withClass("form-control-filer")
                                                                                                                                                    .withType("file"),
                                                                                                                                            img().withId("priew-ava").withSrc("/img/no-ava.png")
                                                                                                                                                    .withSrc(user.getAvatar())

                                                                                                                                    ).withClass("col-8")
                                                                                                                            ).withClass("form-group row"),

                                                                                                                            div(
                                                                                                                                    label("Email*").attr("for","email")
                                                                                                                                            .withClass("col-4 col-form-label"),
                                                                                                                                    div(
                                                                                                                                            input().withId("email")
                                                                                                                                                    .withName("email")
                                                                                                                                                    .withPlaceholder("Email")
                                                                                                                                                    .withClass("form-control here")
                                                                                                                                                    .isRequired()
                                                                                                                                                    .withType("email")
                                                                                                                                                    .withValue(user.getEmail())
                                                                                                                                    ).withClass("col-8")
                                                                                                                            ).withClass("form-group row"),

                                                                                                                            div(
                                                                                                                                    label("First name*").attr("for","firstName")
                                                                                                                                            .withClass("col-4 col-form-label"),
                                                                                                                                    div(
                                                                                                                                            input().withId("firstName")
                                                                                                                                                    .withName("firstName")
                                                                                                                                                    .withPlaceholder("First name")
                                                                                                                                                    .withClass("form-control here")
                                                                                                                                                    .isRequired()
                                                                                                                                                    .withType("text")
                                                                                                                                                    .withValue(user.getFirstName())
                                                                                                                                    ).withClass("col-8")
                                                                                                                            ).withClass("form-group row"),

                                                                                                                            div(
                                                                                                                                    label("Last name*").attr("for","lastName")
                                                                                                                                            .withClass("col-4 col-form-label"),
                                                                                                                                    div(
                                                                                                                                            input().withId("lastName")
                                                                                                                                                    .withName("lastName")
                                                                                                                                                    .withPlaceholder("Last name")
                                                                                                                                                    .withClass("form-control here")
                                                                                                                                                    .isRequired()
                                                                                                                                                    .withType("text")
                                                                                                                                                    .withValue(user.getLastName())
                                                                                                                                    ).withClass("col-8")
                                                                                                                            ).withClass("form-group row"),

                                                                                                                            div(
                                                                                                                                    label("Password*").attr("for","password")
                                                                                                                                            .withClass("col-4 col-form-label"),
                                                                                                                                    div(
                                                                                                                                            input().withId("password")
                                                                                                                                                    .withName("password")
                                                                                                                                                    .withPlaceholder("password")
                                                                                                                                                    .withClass("form-control here")
                                                                                                                                                    .withType("text")
                                                                                                                                                    .withValue(user.getPassword())
                                                                                                                                    ).withClass("col-8")
                                                                                                                            ).withClass("form-group row"),

                                                                                                                            div(
                                                                                                                                    div(
                                                                                                                                            button("Save")
                                                                                                                                                    .withName("submit")
                                                                                                                                                    .withType("submit")
                                                                                                                                                    .withClass("btn btn-primary")
                                                                                                                                    ).withClass("offset-4 col-8")
                                                                                                                            ).withClass("form-group row")
                                                                                                                    )
                                                                                                            ).withClass("col-md-12")

                                                                                                    ).withClass("row")
                                                                                            ).withClass("table-responsive").withStyle("overflow: hidden;")
                                                                                    ).withClass("card-body")
                                                                                    ).withClass("table-wrapper")
                                                                            ).withClass("container")
                                                                    ).withClass("card mb-4")

                                                            ).withClass("container-fluid")
                                                    )
                                            ),  FOOTER,

                                            script(rawHtml(" $(document)\n" +
                                                    "        .on(\"click\",\"#priew-ava\", function () {\n" +
                                                    "\n" +
                                                    "            $(\"#avatar\").click();\n" +
                                                    "        });\n" +
                                                    "            function readURL(input) {\n" +
                                                    "                if (input.files && input.files[0]) {\n" +
                                                    "                    var reader = new FileReader();\n" +
                                                    "\n" +
                                                    "                    reader.onload = function(e) {\n" +
                                                    "                        $('#priew-ava').attr('src', e.target.result);\n" +
                                                    "                    }\n" +
                                                    "\n" +
                                                    "                    reader.readAsDataURL(input.files[0]); // convert to base64 string\n" +
                                                    "                }\n" +
                                                    "            }\n" +
                                                    "\n" +
                                                    "            $(\"#avatar\").change(function() {\n" +
                                                    "                readURL(this);\n" +
                                                    "            });"))

                                    ).withId("layoutSidenav_content")

                            ).withId("layoutSidenav")

                    ).withClass("sb-nav-fixed"));

            response.getWriter().println(homeHtml.render());

        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String avatar = request.getParameter("avatar");
        String role = request.getParameter("role");

        if(email != null && role != null){
            userService.updateUserRoleInDB(email, password, firstName, lastName, avatar, role);
            response.sendRedirect("/admin/user-management");
        }
    }

}
