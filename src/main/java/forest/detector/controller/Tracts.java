package forest.detector.controller;

import forest.detector.dao.entity.Tract;
import forest.detector.dao.repository.TractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "tracts", urlPatterns = {"/tracts"})
public class Tracts extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(Index.class);
    private TractRepository tractRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited tracts page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        tractRepository = new TractRepository((DataSource) request.getServletContext().getAttribute("datasource"));
        PrintWriter out = response.getWriter();
        int ticketID;
        if (request.getParameter("ticket-id") == null) {
            response.sendRedirect("/table");
        } else {
            ticketID = Integer.parseInt(request.getParameter("ticket-id"));
            List <Tract> list = tractRepository.getTracts(ticketID);
            String html = "<html><body><table>";
            for (int i = 0; i < list.size(); i++) {
                html += "<tr><td>" + list.get(i).getQuarter() + "</td>";
                html += "<td>" + list.get(i).getDivision() + "</td>";
                html += "<td>" + list.get(i).getRange() + "</td>";
                html += "<td>" + list.get(i).getArea() + "</td>";
                html += "<td>" + list.get(i).getForestType() + "</td>";
                html += "<td>" + list.get(i).getGeneralAllowedExtent() + "</td>";
                html += "<td>" + list.get(i).getContributor() + "</td></tr>";
            }
            html += "</body></html>";
            out.println(html);
        }
    }
}
