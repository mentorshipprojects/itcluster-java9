package forest.detector.service;

import forest.detector.dao.entity.Ticket;
import forest.detector.dao.entity.Tract;
import forest.detector.dao.repository.TicketRepository;
import forest.detector.dao.repository.TractRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HtmlParser {

    private TicketRepository ticketRepository;
    private TractRepository tractRepository;

    public HtmlParser(DataSource dataSource) {
        ticketRepository = new TicketRepository(dataSource);
        tractRepository = new TractRepository(dataSource);
    }

    public void ticketParser() throws IOException, ParseException {
        Ticket ticket = new Ticket();
        int pageNumber = 1;
        boolean isLastPage = false;
        while (!isLastPage) {
            String url = "https://lk.ukrforest.com/forest-tickets/index?TicketSearchPublic[region_id]=10&page=" + pageNumber;
            Document document = Jsoup.connect(url).get();
            Element tbody = document.select("tbody").get(0); // table
            Elements row = tbody.select("tr"); // List of rows
            Element ul = document.select("ul").get(1);
            Elements nextButtonClass = ul.select("li");
            String[][] table = elementsToArray(row);

            for (int i = 0; i < table[0].length; i++) {
                ticket.setNumber(table[2][i]);
                ticket.setRegion(table[0][i]);
                ticket.setForestUser(table[1][i]);
                ticket.setStartDate(sqlDate(table[3][i]));
                ticket.setFinishDate(sqlDate(table[4][i]));
                ticket.setForestry(table[5][i]);
                ticket.setCuttingType(table[6][i]);
                ticket.setTicketStatus(table[7][i]);
                ticket.setCuttingStatus(table[8][i]);
                if (ticketRepository.save(ticket)) {
                    tractParser(table[9][i], table[2][i]);
                }
            }
            if (isLastPage(nextButtonClass)) {
                isLastPage = true;
            } else {
                pageNumber++;
                System.out.println("\nNEW PAGE " + pageNumber + "\n");
            }
        }
    }

    public void tractParser(String tractLink, String ticketNumber) throws IOException {
        Tract tract = new Tract();
        String url = "https://lk.ukrforest.com" + tractLink;
        Document document = Jsoup.connect(url).get();
        Element tbody = document.select("tbody").get(1); // table
        Elements row = tbody.select("tr"); // List of rows
        String[][] table = elementsToArray(row);
        for (int i = 0; i < table[0].length; i++) {
            tract.setTicketNumber(ticketNumber);
            tract.setQuarter(table[0][i]);
            tract.setDivision(table[1][i]);
            tract.setRange(table[2][i]);
            tract.setArea(Float.parseFloat(table[3][i]));
            tract.setForestType(table[4][i]);
            tract.setGeneralAllowedExtent(Float.parseFloat(table[5][i]));
            tract.setAllowedExtent(Float.parseFloat(table[6][i]));
            tract.setCuttingStatus(table[7][i]);
            tract.setContributor(table[8][i]);
            tract.setMapId(table[9][i]);
            tractRepository.save(tract);
        }
    }

    public static boolean isLastPage(Elements nextButtonClass) throws IOException {
        return nextButtonClass.last().select("li").attr("class").equals("next disabled");
    }


    public static void pageCounter() throws IOException {
        int pageNumber = 1;
        boolean isCounted = false;
        System.out.print("Counting pages: ");
        while (!isCounted) {
            String url = "https://lk.ukrforest.com/forest-tickets/index?TicketSearchPublic[region_id]=10&page=" + pageNumber;
            Document document = Jsoup.connect(url).get();
            Element ul = document.select("ul").get(1); // table
            Elements next = ul.select("li"); // List of rows
            System.out.print(pageNumber + " ");
            if (next.last().select("li").attr("class").equals("next disabled")) {
                System.out.println("--> " + pageNumber);
                isCounted = true;
            }
            pageNumber += 1;
        }
    }

    public static String[][] elementsToArray(Elements elements) throws IOException {
        int columnSize = elements.get(0).select("td").size();
        int rowSize = elements.size();
        String[][] array = new String[columnSize][rowSize];
        for (int i = 0; i < array[0].length; i++) {
            for (int k = 0; k < array.length; k++) {
                if (k < array.length - 1) {
                    array[k][i] = (elements.get(i).select("td").get(k).text()).replaceAll("'", "''");
                } else {
                    array[k][i] = elements.get(i).select("td").get(k).select("a").get(0).attr("href");
                }
            }
        }
        return array;
    }

    public static java.sql.Date sqlDate(String date) throws ParseException {
        Date oldDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return new java.sql.Date(oldDate.getTime());
    }
}

