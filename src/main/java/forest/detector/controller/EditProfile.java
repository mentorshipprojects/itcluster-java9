package forest.detector.controller;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static forest.detector.utils.AdminTemplates.*;
import static forest.detector.utils.AdminTemplates.FOOTER;
import static forest.detector.utils.EditProfileTemplates.ADM_PASS;
import static forest.detector.utils.EditProfileTemplates.EDIT_PROF_FORM;
import static j2html.TagCreator.*;
import static j2html.TagCreator.th;
@WebServlet(name = "edit_profile", urlPatterns = "/admin/edit_profile")
public class EditProfile extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(TemplateController.class);

    /**
     * <script src="https://kit.fontawesome.com/aac0f778d8.js" crossorigin="anonymous"></script>
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        log.info("Visited Edit Profile page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(
                title("Edit Profile"),
                link().withHref("/css/user-management.css").withRel("stylesheet"),
                HEAD,
                body(
                        NAV,
                        div(

                                div(

                                        MENU

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
                                                                                                        h2(text("Edit "),b("Profile"))
                                                                                                ).withClass("col-sm-5"),
                                                                                                div(

                                                                                                ).withClass("col-sm-7")

                                                                                        ).withClass("row")
                                                                                ).withClass("table-title"),

                                                                                        div(
                                                                                                div(

                                                                                                        div(


                                                                                                                form(
                                                                                                                        div(
                                                                                                                                label("Role").attr("for","nickname")
                                                                                                                                        .withClass("col-4 col-form-label"),
                                                                                                                                div(
                                                                                                                                        select(
                                                                                                                                                option("Admin").withValue("admin"),
                                                                                                                                                option("Moder").withValue("moder"),
                                                                                                                                                option("User").withValue("user")

                                                                                                                                        ).withId("role")
                                                                                                                                                .withName("role")
                                                                                                                                                .withClass("custom-select")
                                                                                                                                                .withType("text")
                                                                                                                                        // в .withValue("") повинна бути змінна з ніком юзера якого редагуємо
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
                                                                                                                                        // в .withSrc() повинна бути змінна з лінком аватара того юзера якого редагуємо

                                                                                                                                ).withClass("col-8")
                                                                                                                        ).withClass("form-group row"),


                                                                                                                        div(
                                                                                                                                label("Nickname*").attr("for","nickname")
                                                                                                                                        .withClass("col-4 col-form-label"),
                                                                                                                                div(
                                                                                                                                        input().withId("nickname")
                                                                                                                                                .withName("nickname")
                                                                                                                                                .withPlaceholder("Nickname")
                                                                                                                                                .withClass("form-control here")
                                                                                                                                                .isRequired()
                                                                                                                                                .withType("text")
                                                                                                                                        // в .withValue("") повинна бути змінна з ніком юзера якого редагуємо
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
                                                                                                                                        // в .withValue("") повинна бути змінна з мейлом юзера якого редагуємо
                                                                                                                                ).withClass("col-8")
                                                                                                                        ).withClass("form-group row"),

                                                                                                                        ADM_PASS,



                                                                                                                        div(

                                                                                                                                div(
                                                                                                                                        button("Save")
                                                                                                                                                .withName("submit")
                                                                                                                                                .withType("submit")
                                                                                                                                                .withClass("btn btn-primary")
                                                                                                                                ).withClass("offset-4 col-8")
                                                                                                                        ).withClass("form-group row")
                                                                                                        ).withClass("col-md-12")

                                                                                                ).withClass("row")
                                                                                        ).withClass("table-responsive").withStyle("overflow: hidden;")
                                                                                ).withClass("card-body")
                                                                        ).withClass("table-wrapper")
                                                                ).withClass("container")

                                                        ).withClass("card mb-4")

                                                        ).withClass("container-fluid"))


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


                ).withClass("sb-nav-fixed")
        );
        response.getWriter().println(homeHtml.render());
    }
}
