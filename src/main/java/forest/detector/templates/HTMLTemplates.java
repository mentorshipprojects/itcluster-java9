package forest.detector.templates;

import com.google.inject.internal.asm.$ClassReader;
import forest.detector.dao.entity.User;
import forest.detector.service.TicketService;
import forest.detector.service.UserService;
import j2html.TagCreator;
import j2html.tags.ContainerTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import static j2html.TagCreator.*;

public class HTMLTemplates {

    public static final ContainerTag HEAD = head(
            title("Forest"),
            link().withHref("/css/home-page.css")
                    .withRel("stylesheet"),
            link().withHref("/css/mdi/css/materialdesignicons.min.css")
                    .withRel("stylesheet"),
            link().withHref("https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css")
                    .withRel("stylesheet")
                    .attr("crossorigin","anonymous"),
            script().withSrc("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js")
                    .attr("crossorigin","anonymous"),
            script().withSrc("/js/vendor.bundle.base.js")
    );

    public static final ContainerTag NAV_LOGOUT = div(
            nav(
                    div(
                            div(
                                    a("Forest").withClass("navbar-brand")
                            ).withClass("text-center navbar-brand-wrapper d-flex align-items-center justify-content-center"),
                            div(
                                    ul(
                                            li(
                                                    a(
                                                            div(
                                                                    img()
                                                                            .attr("alt","image")
                                                                            .withSrc("")
                                                            ).withClass("nav-profile-img")
                                                    ).withClass("nav-link")
                                                            .withId("profileDropdown")
                                                            .withHref("#")
                                                            .attr("data-toggle","dropdown")
                                                            .attr("aria-expanded","false"),

                                                    div(

                                                            a(
                                                                    i().withClass("mdi mdi-arrow-down-bold-circle-outline mr-2 text-success"), text(" Logout")
                                                            ).withClass("dropdown-item")
                                                                    .withHref("/logout")
                                                    ).withClass("dropdown-menu navbar-dropdown")
                                                            .attr("aria-labelledby","profileDropdown")

                                            ).withClass("nav-item nav-profile dropdown")
                                    ).withClass("navbar-nav navbar-nav-right"),
                                    button(
                                            span().withClass("mdi mdi-menu")
                                    ).withClass("navbar-toggler navbar-toggler-right d-lg-none align-self-center")
                                            .attr("type","button")
                                            .attr("data-toggle","horizontal-menu-toggle")

                            ).withClass("navbar-menu-wrapper d-flex align-items-center justify-content-end")
                    ).withClass("container")
            ).withClass("navbar top-navbar col-lg-12 col-12 p-0")
    ).withClass("horizontal-menu");

    public static final ContainerTag ADM_NAV = div(
            nav(
                    div(
                            div(
                                    a("Forest").withClass("navbar-brand")
                            ).withClass("text-center navbar-brand-wrapper d-flex align-items-center justify-content-center"),
                            div(
                                    ul(
                                            li(
                                                    a(
                                                            div(
                                                                    img()
                                                                            .attr("alt","image")
                                                                            .withSrc("/img/no-ava.png")
                                                            ).withClass("nav-profile-img")
                                                    ).withClass("nav-link")
                                                            .withId("profileDropdown")
                                                            .withHref("#")
                                                            .attr("data-toggle","dropdown")
                                                            .attr("aria-expanded","false"),

                                                    div(

                                                            a(
                                                                    i().withClass("mdi mdi-crown mr-2 text-success"), text(" Admin panel ")
                                                            ).withClass("dropdown-item")
                                                                    .withHref("/admin"),
                                                            a(
                                                                    i().withClass("mdi mdi-settings mr-2 text-success"), text(" Settings ")
                                                            ).withClass("dropdown-item")
                                                                    .withHref("/settings"),

                                                            a(
                                                                    i().withClass("mdi mdi-logout mr-2 text-success"), text(" Logout ")
                                                            ).withClass("dropdown-item")
                                                                    .withHref("/logout")
                                                    ).withClass("dropdown-menu navbar-dropdown")
                                                            .attr("aria-labelledby","profileDropdown")

                                            ).withClass("nav-item nav-profile dropdown")
                                    ).withClass("navbar-nav navbar-nav-right"),
                                    button(
                                            span().withClass("mdi mdi-menu")
                                    ).withClass("navbar-toggler navbar-toggler-right d-lg-none align-self-center")
                                            .attr("type","button")
                                            .attr("data-toggle","horizontal-menu-toggle")

                            ).withClass("navbar-menu-wrapper d-flex align-items-center justify-content-end")
                    ).withClass("container")
            ).withClass("navbar top-navbar col-lg-12 col-12 p-0")
    ).withClass("horizontal-menu");
    public static final ContainerTag ADM_UL = ul(
            li(
                    a(
                            div(
                                    img()
                                            .attr("alt","image")
                                            .withSrc("/img/no-ava.png")
                            ).withClass("nav-profile-img")
                    ).withClass("nav-link")
                            .withId("profileDropdown")
                            .withHref("#")
                            .attr("data-toggle","dropdown")
                            .attr("aria-expanded","false"),

                    div(

                            a(
                                    i().withClass("mdi mdi-crown mr-2 text-success"), text(" Admin panel ")
                            ).withClass("dropdown-item")
                                    .withHref("/admin"),
                            a(
                                    i().withClass("mdi mdi-settings mr-2 text-success"), text(" Settings ")
                            ).withClass("dropdown-item")
                                    .withHref("/settings"),

                            a(
                                    i().withClass("mdi mdi-logout mr-2 text-success"), text(" Logout ")
                            ).withClass("dropdown-item")
                                    .withHref("/logout")
                    ).withClass("dropdown-menu navbar-dropdown")
                            .attr("aria-labelledby","profileDropdown")

            ).withClass("nav-item nav-profile dropdown")
    ).withClass("navbar-nav navbar-nav-right");

    public static final ContainerTag USR_UL = ul(
            li(
                    a(
                            div(
                                    img()
                                            .attr("alt","image")
                                            .withSrc("/img/no-ava.png")
                            ).withClass("nav-profile-img")
                    ).withClass("nav-link")
                            .withId("profileDropdown")
                            .withHref("#")
                            .attr("data-toggle","dropdown")
                            .attr("aria-expanded","false"),

                    div(


                            a(
                                    i().withClass("mdi mdi-settings mr-2 text-success"), text(" Settings ")
                            ).withClass("dropdown-item")
                                    .withHref("/settings"),

                            a(
                                    i().withClass("mdi mdi-logout mr-2 text-success"), text(" Logout ")
                            ).withClass("dropdown-item")
                                    .withHref("/logout")
                    ).withClass("dropdown-menu navbar-dropdown")
                            .attr("aria-labelledby","profileDropdown")

            ).withClass("nav-item nav-profile dropdown")
    ).withClass("navbar-nav navbar-nav-right");
  public static  final ContainerTag GUEST_UL = ul(

          li(
                  a("Sign in").withClass("btn btn-outline-success").withHref("/login")

          ).withClass("nav-item"),
          li(
                  a("Sign up").withClass("btn btn-outline-primary").withHref("/register")

          ).withClass("nav-item")
  ).withClass("navbar-nav navbar-nav-right");
    public static final ContainerTag NAV (HttpSession session) {

        String role = (String) session.getAttribute("role");

        return  div(

                nav(
                        div(
                                a("Forest").withClass("navbar-brand").withHref("/home")
                                , div(
                                        ul(
                                                li(
                                                        a("Home")
                                                                .withClass("nav-link")
                                                                .withHref("/home")
                                                ).withClass("nav-item"),
                                                li(
                                                        a("Analytics")
                                                                .withClass("nav-link")
                                                                .withHref("/analytics")
                                                ).withClass("nav-item"),
                                                li(
                                                        a("Table")
                                                                .withClass("nav-link")
                                                                .withHref("/table")
                                                ).withClass("nav-item")

                                        ).withClass("navbar-nav mr-auto")
                                ).withClass("collapse navbar-collapse").withId("navbarSupportedContent")

                        ).withClass("container"),

                        div(
//                                TagCreator.iff(role=null,GUEST_UL,)

                                iffElse(role==null, GUEST_UL, USR_UL)
//                                iffElse(role.equals("admin"), ADM_UL, GUEST_UL)
//                                iff(role.equals("admin"), ADM_UL)
//                                iffElse(role==null, GUEST_UL,iffElse(role.equals("admin"), ADM_UL, GUEST_UL))


//                                iffElse(role.equals("admin"), ADM_UL, USR_UL)



                        ).withClass("navbar-menu-wrapper d-flex align-items-center justify-content-end"),
                        button(span().withClass("navbar-toggler-icon")).withClass("navbar-toggler")
                                .attr("data-toggle", "collapse")
                                .attr("data-target", "#navbarSupportedContent")
                                .attr("aria-controls", "navbarSupportedContent")
                                .attr("aria-expanded", "false")
                                .attr("aria-label", "Toggle navigation")
                ).withClass("navbar navbar-expand-lg navbar-light bg-light navbar top-navbar col-lg-12 col-12 p-0")

        ).withClass("horizontal-menu");


    }

//            nav(
//                    div(
//                            div(
//                                    a("Forest").withClass("navbar-brand")
//                            ).withClass("text-center navbar-brand-wrapper d-flex align-items-center justify-content-center"),
//                            div(
//                                    ul(
//                                            li(
//                                                    a("Sign in").withClass("btn btn-outline-success").withHref("/login")
//
//                                            ).withClass("nav-item"),
//                                            li(
//                                                    a("Sign up").withClass("btn btn-outline-primary").withHref("/register")
//
//                                            ).withClass("nav-item")
//                                    ).withClass("navbar-nav navbar-nav-right"),
//                                    button(
//                                            span().withClass("mdi mdi-menu")
//                                    ).withClass("navbar-toggler navbar-toggler-right d-lg-none align-self-center")
//                                            .attr("type","button")
//                                            .attr("data-toggle","horizontal-menu-toggle")
//
//                            ).withClass("navbar-menu-wrapper d-flex align-items-center justify-content-end")
//                    ).withClass("container")
//            ).withClass("navbar top-navbar col-lg-12 col-12 p-0")
//    ).withClass("horizontal-menu");

    public static final ContainerTag GRAPH = div(
            div(
                    div(
                            div(
                                    i().withClass("fas fa-chart-area mr-1"),
                                    text("Area Chart Example")
                            ).withClass("card-header"),
                            div(
                                    canvas().withId("myAreaChart").attr("width","100%")
                                            .attr("height","40")
                            ).withClass("card-body")

                    ).withClass("card mb-4")
            ).withClass("col-xl-6 full")

    ).withClass("row");

    public static  ContainerTag TABLE = div(
            div(i().withClass("fas fa-table mr-1"),text("DataTable Example")
            ).withClass("card-header"),
            div(
                    div(

                            table(
                                    thead(
                                            tr(
                                                    th("Number"),
                                                    th("Region"),
                                                    th("Forest user"),
                                                    th("Start date"),
                                                    th("Finish date"),
                                                    th("Forestry"),
                                                    th("Cutting type"),
                                                    th("Ticket status"),
                                                    th("Cutting status")
                                            )
                                    ),
                                    tfoot(
                                            tr(
                                                    th("Number"),
                                                    th("Region"),
                                                    th("Forest user"),
                                                    th("Start date"),
                                                    th("Finish date"),
                                                    th("Forestry"),
                                                    th("Cutting type"),
                                                    th("Ticket status"),
                                                    th("Cutting status")
                                            )

                                    ),
                                    tbody(
                                            tr(
                                                    th("45645645"),
                                                    th("IF"),
                                                    th("Admin"),
                                                    th("22.05.2020"),
                                                    th("22.05.2020"),
                                                    th("N/A"),
                                                    th("N/A"),
                                                    th("Ok"),
                                                    th("Ok")
                                            ),
                                            tr(
                                                    th("45645645"),
                                                    th("IF"),
                                                    th("Admin"),
                                                    th("22.05.2020"),
                                                    th("22.05.2020"),
                                                    th("N/A"),
                                                    th("N/A"),
                                                    th("Ok"),
                                                    th("Ok")
                                            )
                                    )


                            ).withClass("table table-bordered")
                                    .withId("dataTable").attr("width","100%")
                                    .attr("cellspacing","0")

                    ).withClass("table-responsive")

            ).withClass("card-body")

    ).withClass("card mb-4");
    public static final ContainerTag FOOTER = footer(
            div(i().withClass("fa fa-chevron-up")).withClass("scrollup"),
            div(
                    div(
                            div(
                                    a(
                                            i().withClass("fab fa-telegram").withStyle("color: #007bff;")
                                    ).withHref("https://t.me/Java9ProjectBot")
                                            .withClass("tg-link")
                                            .attr("data-toggle","popover")
                                            .attr("data-placement","top")
                                            .attr("data-trigger","hover")
                                            .attr("data-content","Use our telegram bot")
                            ).withClass("myPopover")
                    ).withClass("d-sm-flex justify-content-center justify-content-sm-between")

            ).withClass("container"),
            script().withSrc("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"),



            script().withSrc("https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js")
                    .attr("crossorigin", "anonymous"),

            script().withSrc("https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js")
                    .attr("crossorigin", "anonymous"),

            script().withSrc("/js/datatables-demo.js"),
            script().withSrc("/js/chart-area-demo.js"),

            script().withSrc("/js/chart-bar-demo.js"),
            script().withSrc("/js/chart-pie-demo.js")

    ).withClass("footer");
}