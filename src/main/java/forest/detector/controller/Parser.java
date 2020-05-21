package forest.detector.controller;

import forest.detector.service.HtmlParser;
import forest.detector.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;


@WebServlet(name = "Parser", urlPatterns = "/parser")
public class Parser extends HttpServlet {
    private UserService userService;
    private static Logger log = LoggerFactory.getLogger(Parser.class);
    private HtmlParser parser;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Parser page.");
        parser = new HtmlParser((DataSource) request.getServletContext().getAttribute("datasource"));
        try {
            parser.ticketParser();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
