package forest.detector.controller.dataBase;

import forest.detector.dao.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;


@WebServlet(name = "stopParser", urlPatterns = "/parser-stop")
public class ParserStopController extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(ParserStopController.class);
    private TicketRepository ticketRepository;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(300*60);
        String role = (String) session.getAttribute("role");

        if(role == null)
            response.sendRedirect("/home");
        else if(role.equals("admin")) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
            ticketRepository = new TicketRepository((DataSource) request.getServletContext().getAttribute("datasource"));
            int[] i = ticketRepository.statusUpload();
            ticketRepository.stopParsing(i[3]);
        }
    }
}
