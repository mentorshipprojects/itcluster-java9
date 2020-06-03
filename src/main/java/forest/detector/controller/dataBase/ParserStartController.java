package forest.detector.controller.dataBase;

import forest.detector.utils.HtmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;


@WebServlet(name = "startParser", urlPatterns = "/parser-start")
public class ParserStartController extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(ParserStartController.class);
    private HtmlParser parser;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if(role == null)
            response.sendRedirect("/home");
        else if(role.equals("admin")){

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

            parser = new HtmlParser((DataSource) request.getServletContext().getAttribute("datasource"));
            PrintWriter out = response.getWriter();
            try {
                parser.ticketParser();
            } catch (ParseException e) {
                log.error("Parsing FAILED ", e);
            }
        }
    }
}
