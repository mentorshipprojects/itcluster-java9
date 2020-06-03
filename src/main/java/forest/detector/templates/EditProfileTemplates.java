package forest.detector.templates;

import j2html.tags.ContainerTag;

import static j2html.TagCreator.*;
import static j2html.TagCreator.button;
public class EditProfileTemplates {

    public static final ContainerTag ADM_PASS = div(
            label("Password*").attr("for","password")
                    .withClass("col-4 col-form-label"),
            div(
                    input().withId("password")
                            .withName("password")
                            .withPlaceholder("password")
                            .withClass("form-control here")

                            .withType("password")
            ).withClass("col-8")
    ).withClass("form-group row");
    public static final ContainerTag USR_PASS = div(
            div(
            label("Old password*").attr("for","old_password")
                    .withClass("col-4 col-form-label"),
            div(
                    input().withId("old_password")
                            .withName("old_password")
                            .withPlaceholder("Old password")
                            .withClass("form-control here")

                            .withType("password")
            ).withClass("col-8")
    ).withClass("form-group row"),
    div(
            label("New password*").attr("for","new_password").withClass("col-4 col-form-label"),
    div(
            input().withId("new_password")
                                                                                                                                                .withName("new_password")
                                                                                                                                                .withPlaceholder("New password")
                                                                                                                                                .withClass("form-control here")

                                                                                                                                                .withType("password")
                                                                                                                                ).withClass("col-8")
                                                                                                                        ).withClass("form-group row"),

    div(
            label("Confirm new password*").attr("for","confirm_new_password")
                                                                                                                                        .withClass("col-4 col-form-label"),
    div(
            input().withId("confirm_new_password")
                                                                                                                                                .withName("confirm_new_password")
                                                                                                                                                .withPlaceholder("Confirm new password")
                                                                                                                                                .withClass("form-control here")

                                                                                                                                                .withType("password")
                                                                                                                                ).withClass("col-8")
                                                                                                                        ).withClass("form-group row")
    );

    public static final ContainerTag EDIT_PROF_FORM = form().withMethod("post").with(
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

           USR_PASS,




            div(

            div(
            button("Save")
                                                                                                                                                .withName("submit")
                                                                                                                                        .withType("submit")
                                                                                                                                        .withClass("btn btn-primary")
                                                                                                                                ).withClass("offset-4 col-8")
                                                                                                                        ).withClass("form-group row")





                                                                                                                );
}
