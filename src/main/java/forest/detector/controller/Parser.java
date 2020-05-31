package forest.detector.controller;

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


@WebServlet(name = "Parser", urlPatterns = "/parser")
public class Parser extends HttpServlet {
    private static Logger log = LoggerFactory.getLogger(Parser.class);
    private HtmlParser parser;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Parser start.");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        parser = new HtmlParser((DataSource) request.getServletContext().getAttribute("datasource"));
        PrintWriter out = response.getWriter();
        try {
            int i[] = parser.ticketParser();
            out.print("БАЗУ ДАНИХ УСПІШНО ОНОВЛЕНО!\n\n" +
                    "Лісорубні квитки: додано " + i[0] + ", оновлено " + i[1] + ", перевірено " + i[2] + "\n" +
                    "Лісорубні ділянки: додано " + i[3] + ",  оновлено " + i[4] + ",  перевірено " + i[5]);
        } catch (ParseException e) {
            log.error("Parsing FAILED ", e);
        }
    }

}
