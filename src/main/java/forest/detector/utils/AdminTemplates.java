package forest.detector.utils;

import j2html.tags.ContainerTag;

import static j2html.TagCreator.*;
import static j2html.TagCreator.button;

public class AdminTemplates {

    public static final ContainerTag HEAD = head(
            title("Admin page"),
            // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/script
            meta().attr("charset", "UTF-8"),
            meta().attr("name", "UTF-8").attr("content", "width=device-width, initial-scale=1.0"),
            meta().attr("http-equiv", "X-UA-Compatible").attr("content", "ie=edge"),
            link().withRel("stylesheet").withHref("/css/admin-page.css"),
            link().withRel("stylesheet").withHref("https://fonts.googleapis.com/icon?family=Material+Icons"),
            link()
                    .withRel("stylesheet")
                    .withHref("https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"),
            script().withSrc("https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("/js/nprogress.js"),
            link().withRel("stylesheet").withHref("/css/nprogress.css")
    );

    public static final ContainerTag GRAPH = div(
            div(
                    div(
                            div(
                                    i().withClass("fas fa-chart-area mr-1"),
                                    text("Area Chart Example")).withClass("card-header"),
                            div(
                                    canvas().withId("myAreaChart")
                                            .attr("width", "100%")
                                            .attr("height", "40")

                            ).withClass("card-body")

                    ).withClass("card mb-4")
            ).withClass("col-xl-6"),
            div(
                    div(
                            div(
                                    i().withClass("fas fa-chart-bar mr-1"),
                                    text("Bar Chart Example")).withClass("card-header"),
                            div(
                                    canvas().withId("myBarChart")
                                            .attr("width", "100%")
                                            .attr("height", "40")

                            ).withClass("card-body")

                    ).withClass("card mb-4")
            ).withClass("col-xl-6")
    ).withClass("row");

    public static final ContainerTag MENU = nav(
            div(
                    div(
                            div("Main").withClass("sb-sidenav-menu-heading"),
                            a(
                                    div(i().withClass("fas fa-tachometer-alt"))
                                            .withClass("sb-nav-link-icon"), text("Dashboard")).withClass("nav-link").withHref("/admin"),
                            a(
                                    div(i().withClass("fas fa-user"))
                                            .withClass("sb-nav-link-icon"), text("User Management")).withClass("nav-link").withHref("/admin/user-management")

                            ,a(
                                    div(i().withClass("fas fa-sync-alt"))
                                            .withClass("sb-nav-link-icon"), text("Update BD")).withClass("nav-link")
                                    .attr("onclick","bar()")
                            .withId("update-bd")

                            , script(rawHtml("$(document).ready(function(){\n" +
                                    "  $(\"#update-bd\").click(function(){\n" +
                                    "    $.get(\"/parser\", function(data, status){\n" +
                                    "       setTimeout(function() {NProgress.done(); $('.fade').removeClass('out');}, 1000);alert(data);\n" +
                                    "    });\n" +
                                    "  });\n" +
                                    "});" +
                                    "function bar() {\n" +"NProgress.start();"+

                                    "        }"))
                    ).withClass("nav")
            ).withClass("sb-sidenav-menu"),
            div(
                    div("Logged in as:").withClass("small"), text("User Nickname")
            ).withClass("sb-sidenav-footer")
    ).withClass("sb-sidenav accordion sb-sidenav-dark")
            .withId("sidenavAccordion");

    public static final ContainerTag NAV = nav(
            a(text("Admin page")).withClass("navbar-brand"), button(i().withClass("fas fa-bars")).withClass("btn btn-link btn-sm order-1 order-lg-0"
            ).withId("sidebarToggle").withHref("#"),
            form().withClass("d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0"),
            ul(
                    li(a(i().withClass("fas fa-user fa-fw")).withClass("nav-link dropdown-toggle").withId("userDropdown")
                                    .withHref("#").attr("role", "button")
                                    .attr("data-toggle", "dropdown")
                                    .attr("aria-haspopup", "true")
                                    .attr("aria-expanded", "aria-expanded"),
                            div(a("Settings").withClass("dropdown-item")
                                            .withHref("#"),
                                    div().withClass("dropdown-divider"),
                                    a("Logout").withClass("dropdown-item").withHref("/logout")
                            ).withClass("dropdown-menu dropdown-menu-right")
                                    .attr("aria-labelledby", "userDropdown")
                    ).withClass("nav-item dropdown")
            ).withClass("navbar-nav ml-auto ml-md-0")
    ).withClass("sb-topnav navbar navbar-expand navbar-dark bg-dark");//source

    public static final ContainerTag FOOTER = footer(
            div(
                    div().withClass("d-flex align-items-center justify-content-between small")
            ).withClass("container-fluid"),
            script().withSrc("https://code.jquery.com/jquery-3.4.1.min.js")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("/js/scripts.js"),
            script().withSrc("https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js")
                    .attr("crossorigin", "anonymous"),
            //script().withSrc("/js/chart-area-demo.js"),
            script().withSrc("/js/chart-bar-demo.js"),
            script().withSrc("https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js")
                    .attr("crossorigin", "anonymous"),
            script().withSrc("/js/datatables-demo.js"),
            script(rawHtml("Chart.defaults.global.defaultFontFamily = \'-apple-system,system-ui,BlinkMacSystemFont,\"Segoe UI\",Roboto,\"Helvetica Neue\",Arial,sans-serif\';\n" +
                    "Chart.defaults.global.defaultFontColor = \'#292b2c\';\n" +
                    "\n" +
                    "// Area Chart Example\n" +
                    "var ctx = document.getElementById(\"myAreaChart\");\n" +
                    "var myLineChart = new Chart(ctx, {\n" +
                    "  type: \'line\',\n" +
                    "  data: {\n" +
                    "    labels: [\"\", \"2009\", \"2010\", \"2011\", \"2012\", \"2013\", \"2014\", \"2015\", \"2016\", \"2017\", \"2018\", \"2019\", \"2020\"],\n" +
                    "    datasets: [{\n" +
                    "      label: \"Sessions\",\n" +
                    "      lineTension: 0.3,\n" +
                    "      backgroundColor: \"rgba(2,117,216,0.2)\",\n" +
                    "      borderColor: \"rgba(2,117,216,1)\",\n" +
                    "      pointRadius: 5,\n" +
                    "      pointBackgroundColor: \"rgba(2,117,216,1)\",\n" +
                    "      pointBorderColor: \"rgba(255,255,255,0.8)\",\n" +
                    "      pointHoverRadius: 5,\n" +
                    "      pointHoverBackgroundColor: \"rgba(2,117,216,1)\",\n" +
                    "      pointHitRadius: 50,\n" +
                    "      pointBorderWidth: 2,\n" +
                    "      data: [10000, 30162, 26263, 18394, 18287, 28682, 31274, 33259, 25849, 24159, 32651, 31984, 38451],\n" +
                    "    }],\n" +
                    "  },\n" +
                    "  options: {\n" +
                    "    scales: {\n" +
                    "      xAxes: [{\n" +
                    "        time: {\n" +
                    "          unit: 'date'\n" +
                    "        },\n" +
                    "        gridLines: {\n" +
                    "          display: false\n" +
                    "        },\n" +
                    "        ticks: {\n" +
                    "          maxTicksLimit: 7\n" +
                    "        }\n" +
                    "      }],\n" +
                    "      yAxes: [{\n" +
                    "        ticks: {\n" +
                    "          min: 0,\n" +
                    "          max: 40000,\n" +
                    "          maxTicksLimit: 5\n" +
                    "        },\n" +
                    "        gridLines: {\n" +
                    "          color: \"rgba(0, 0, 0, .125)\",\n" +
                    "        }\n" +
                    "      }],\n" +
                    "    },\n" +
                    "    legend: {\n" +
                    "      display: false\n" +
                    "    }\n" +
                    "  }\n" +
                    "});\n")).withType("text/javascript")
    ).withClasses("py-4 bg-light mt-auto");

    public static final ContainerTag PARSER_START_BUTTON = div(
            script().withSrc("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"),
            script(rawHtml("$(document).ready(function(){\n" +
                    "  $(\"input\").click(function(){\n" +
                    "    $.get(\"/parser-start\", function(data, status){\n" +
                    "    });\n" +
                    "  });\n" +
                    "});" +
                    "function myStartFunction() {\n" +
                    "            document.getElementById(\"start_parser\").disabled = true;\n" +
                    "            document.getElementById(\"start_parser\").value = \"UPDATING STARTED\";\n" +
                    "            document.getElementById(\"stop_parser\").value = \"STOP\";\n" +
                    "        }")),
            input().withValue("UPDATE DataBase")
                    .withType("button")
                    .withClass("btn btn-primary")
                    .withId("start_parser")
                    .attr("onclick","myStartFunction()")
    );

    public static final ContainerTag PARSER_STOP_BUTTON = div(
            script().withSrc("https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"),
            script(rawHtml("$(document).ready(function(){\n" +
                    "  $(\"input\").click(function(){\n" +
                    "    $.get(\"/parser-stop\", function(data, status){\n" +
                    "    });\n" +
                    "  });\n" +
                    "});" +
                    "function myStopFunction() {\n" +
                    "            document.getElementById(\"start_parser\").disabled = false;\n" +
                    "            document.getElementById(\"start_parser\").value = \"UPDATE DataBase\";\n" +
                    "            document.getElementById(\"stop_parser\").value = \"STOPPED\";\n" +
                    "        }")),
            input().withValue("STOP")
                    .withType("button")
                    .withClass("btn btn-danger")
                    .withId("stop_parser")
                    .attr("onclick","myStopFunction()")
    );
}
