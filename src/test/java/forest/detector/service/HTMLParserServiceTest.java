package forest.detector.service;

import forest.detector.dao.entity.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import javax.sql.DataSource;
import java.io.IOException;

import java.text.ParseException;

import org.apache.commons.dbcp2.BasicDataSource;

import static forest.detector.service.HtmlParser.sqlDate;

public class HTMLParserServiceTest {

    private static DataSource dataSource;

    @BeforeAll
    static void setUp() {
        String DB = "myRemotePG";
        BasicDataSource data = new BasicDataSource();
        String URL;
        switch (DB) {
            case "localMySQL":
                URL = "mysql://localhost/forest?user=root&password=12345678&useTimezone=true&serverTimezone=UTC";
                data.setDriverClassName("com.mysql.cj.jdbc.Driver");
                data.setUrl("jdbc:" + URL);
                data.setUsername("root");
                data.setPassword("12345678");
                break;
            case "myRemotePG":
                URL = "postgresql://ec2-18-235-20-228.compute-1.amazonaws.com:5432/d2v8c240hrit0m";
                data.setDriverClassName("org.postgresql.Driver");
                data.setUrl("jdbc:" + URL);
                data.setUsername("nxmbxvcsvhjnzq");
                data.setPassword("f20928e4b5d7d1c5647cbcdb425ba51751ffb7561f5b3542e620083119b0f53e");
                break;
            case "mentorRomotePG":

                break;
        }
//        System.out.println(data);
        dataSource = data;
    }


    @Disabled
    @Test
    void ticketParserTest() throws ParseException, IOException {
//        Ticket ticket = new Ticket();
//        ticket.setNumber("005036.6");
//        ticket.setStartDate(sqlDate("20.05.2020"));
//        ticket.setFinishDate(sqlDate("31.12.2020"));

//        isInDataBase (ticket, dataSource);

        HtmlParser parser = new HtmlParser(dataSource);
       int [] c = parser.ticketParser();
        System.out.println(c[0]);
        System.out.println(c[1]);

//        parser.ticketParser();

//        Assertions.assertTrue(b, "something goes wrong");
//        System.out.println(dataSource);
    }
}
