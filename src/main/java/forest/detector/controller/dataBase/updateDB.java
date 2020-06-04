package forest.detector.controller.dataBase;


import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static forest.detector.templates.AdminTemplates.*;

import static j2html.TagCreator.*;

@WebServlet(name = "test", urlPatterns = "/admin/db-update")
public class updateDB extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(updateDB.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Parser page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        ContainerTag parserButton = html(HEAD,
                body(
                        NAV,
                        div(
                                PARSER_START_BUTTON
,button("status").withId("star"),
                                script(rawHtml("function status(){ $.getJSON(\"/admin/status\", function(json) {\n" +
                                        "        \n" +
                                        "        console.log(json.status);\n" +
                                        "        console.log(json.total);\n" +
                                        "        console.log(json.is_finished);\n" +
                                        "\n" +
                                        "    });}")),
style(rawHtml(".dpm-menu-stats-list-bar {\n" +
        "    margin-top: 3px;\n" +
        "    height: 4px;\n" +
        "    background: #ddd;\n" +
        "    border-radius: 2px;\n" +
        "}\n" +
        ".dpm-menu-stats-list-bar > div {\n" +
        "    height: 100%;\n" +
        "    background: #777;\n" +
        "    border-radius: 2px;\n" +
        "    border-bottom: 1px solid rgba(0,0,0,.06);\n" +
        "}.dpm-bar2 > div {\n" +
        "     background: #7ac37a;\n" +
        " }"))
,div(div()).withClass("dpm-menu-stats-list-bar dpm-bar2")
                                ,script(rawHtml("function set() {\n" +
                                        "       $.getJSON(\"/admin/status\", function (json) {\n" +
                                        "\n" +
                                        "           $('.dpm-bar2 div').css('width', (100 / (json.total / json.status)).toFixed(2) + '%');\n" +
                                        "if(json.status==json.total){\n" +
                                        "           clearInterval(intervalID);}else{" +
                                        " $(\"#star\").click(function(event) {\n" +
                                        "        event.preventDefault();\n" +
                                        "        event.stopPropagation();\n" +
                                        "        return false;\n" +
                                        "    });         " +
                                        "}\n" +
                                        "\n" +
                                        "           console.log(\"ok \"+json.status+\" \"+json.total)\n" +
                                        "       });\n" +
                                        "\n" +
                                        "\n" +
                                        "   }\n" +
                                        "\n" +
                                        "       var intervalID;\n" +
                                        "   $(document)\n" +
                                        "\n" +
                                        ".on(\"click\",\"#start_parser\", function () {\n" +
                                        "    intervalID=setInterval(set,1000);\n" +
                                        "})$.getJSON(\"/admin/status\", function (json) {if(json.is_finished=0){intervalID=setInterval(set,1000);}}); "))

                        ).attr("align", "center")
                ),
                FOOTER
        );
        response.getWriter().println(parserButton.render());
    }
}