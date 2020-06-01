package forest.detector.controller;

import forest.detector.dao.repository.TicketRepository;
import forest.detector.service.HtmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;


@WebServlet(name = "stopParser", urlPatterns = "/parser-stop")
public class ParserStopController extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(ParserStopController.class);
    private TicketRepository ticketRepository;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ticketRepository = new TicketRepository((DataSource) request.getServletContext().getAttribute("datasource"));
        int[] i = ticketRepository.statusUpload();
        ticketRepository.stopParsing(i[3]);
    }
}
