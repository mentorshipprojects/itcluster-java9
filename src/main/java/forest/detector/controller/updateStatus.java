package forest.detector.controller;

import forest.detector.dao.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "UpdateStatus", urlPatterns = "/admin/status")
public class updateStatus extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(updateStatus.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Update status");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        TicketRepository ticketRepository = new TicketRepository((DataSource) request.getServletContext().getAttribute("datasource"));
        int[] i = ticketRepository.statusUpload();
        PrintWriter out = response.getWriter();
        String json = "{\"status\":\"" + i[0] + "\",\"total\":\"" + i[1] + "\",\"is_finished\":\"" + i[2] + "\"}";
        out.print(json);
    }
}
