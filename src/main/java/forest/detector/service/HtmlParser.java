package forest.detector.service;

import forest.detector.dao.entity.Ticket;
import forest.detector.dao.entity.Tract;
import forest.detector.dao.repository.TicketRepository;
import forest.detector.dao.repository.TractRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HtmlParser {

    private TicketRepository ticketRepository;
    private TractRepository tractRepository;
    private static Logger log = LoggerFactory.getLogger(HtmlParser.class);

    public HtmlParser(DataSource dataSource) {
        ticketRepository = new TicketRepository(dataSource);
        tractRepository = new TractRepository(dataSource);
    }

    /*
     * method ticketParser() returns a counter of changes:
     * [0] – counter of newly added tickets
     * [1] – counter of updated tickets
     * [2] – counter of checked ticket's positions
     * [3] – counter of newly added tracts
     * [4] – counter of updated tracts
     * [5] – counter of checked tract's positions
     * */
    public int[] ticketParser() throws IOException, ParseException {
        Ticket ticket = new Ticket();
        Ticket checkedTicket;
        int ticketID;
        int[] generalCounter = {0, 0, 0, 0, 0, 0};
        int[] tractCounter;
        int pageNumber = 44;
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
                checkedTicket = ticketRepository.check(ticket);
                if (checkedTicket.getId() == -1) {
                    ticketID = ticketRepository.save(ticket);
                    generalCounter[0]++; // adding counter
                } else {
                    ticketID = checkedTicket.getId();
                    ticket.setId(checkedTicket.getId());
                    if (!ticket.equals(checkedTicket)) {
                        ticketRepository.update(ticket);
                        generalCounter[1]++; // updating counter
                    }
                }
                tractCounter = tractParser(table[9][i], ticketID);
                generalCounter[2]++; // checking counter
                generalCounter[3] += tractCounter[0];
                generalCounter[4] += tractCounter[1];
                generalCounter[5] += tractCounter[2];
            }
            if (isLastPage(nextButtonClass)) {
                isLastPage = true;
            } else {
                pageNumber++;
                System.out.println("\nNEW PAGE " + pageNumber + "\n");
            }
        }
        log.info("\n\nParsing finished. " +
                "Tickets: added [" + generalCounter[0] + "], " +
                "updated [" + generalCounter[1] + "], " +
                "checked [" + generalCounter[2] + "]). " +
                "Tracts: added [" + generalCounter[3] + "], " +
                "updated [" + generalCounter[4] + "], " +
                "checked [" + generalCounter[5] + "]\n");
        return generalCounter;
    }

    public int[] tractParser(String tractLink, int ticketID) throws IOException {
        Tract tract = new Tract();
        Tract checkedTract;
        int[] tractCounter = {0, 0, 0};
        String url = "https://lk.ukrforest.com" + tractLink;
        Document document = Jsoup.connect(url).get();
        Element tbody = document.select("tbody").get(1); // table
        Elements row = tbody.select("tr"); // List of rows
        String[][] table = elementsToArray(row);
        for (int i = 0; i < table[0].length; i++) {
            tract.setTicketID(ticketID);
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
            checkedTract = tractRepository.check(tract);
            if (checkedTract.getId() == -1) {
                tractRepository.save(tract);
                tractCounter[0]++; // adding counter
            } else {
                tract.setId(checkedTract.getId());
                if (!tract.equals(checkedTract)) {
                    tractRepository.update(tract);
                    tractCounter[1]++; // update counter
                }
            }
            tractCounter[2]++; // checking counter
        }
        return tractCounter;
    }

    public static boolean isLastPage(Elements nextButtonClass) throws IOException {
        return nextButtonClass.last().select("li").attr("class").equals("next disabled");
    }

    // method counts pages. not used
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