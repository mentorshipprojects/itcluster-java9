package forest.detector.utils;

import j2html.tags.ContainerTag;

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
                .attr("crossorigin","anonymous")
        );

        public static final ContainerTag NAV = div(
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
                                                              i().withClass("mdi mdi-arrow-right-bold-circle-outline mr-2 text-success"), text(" Login")
                                                      ).withClass("dropdown-item")
                                                      .withHref("/login"),
                                                      a(
                                                              i().withClass("mdi mdi-arrow-down-bold-circle-outline mr-2 text-success"), text(" Register")
                                                      ).withClass("dropdown-item")
                                                      .withHref("/register")
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
            ).withClass("col-xl-6 full"),
            div(
                    div(
                            div(
                                    i().withClass("fas fa-chart-bar mr-1"),
                                    text("Bar Chart Example")).withClass("card-header"),
                            div(
                                    canvas().withId("myBarChart")
                                            .attr("width","100%")
                                            .attr("height","40")

                            ).withClass("card-body")

                    ).withClass("card mb-4")
            ).withClass("col-xl-6"),
            div(
                    div(
                            div(
                                    i().withClass("fas fa-chart-pie mr-1"),
                                    text("Pie Chart Example")).withClass("card-header"),
                            div(
                                    canvas().withId("myPieChart")
                                            .attr("width","100%")
                                            .attr("height","40")

                            ).withClass("card-body")

                    ).withClass("card mb-4")
            ).withClass("col-xl-6")
    ).withClass("row");
    public static final ContainerTag TABLE = div(
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


                script().withSrc("/js/vendor.bundle.base.js"),
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
