package forest.detector.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import javax.sql.DataSource;
import java.io.IOException;

import java.text.ParseException;

import org.apache.commons.dbcp2.BasicDataSource;

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
        HtmlParser parser = new HtmlParser(dataSource);
        parser.ticketParser();

//        Assertions.assertTrue(b, "something goes wrong");
//        System.out.println(dataSource);
    }
}
