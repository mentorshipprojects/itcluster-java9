package forest.detector.bot.repository;

import forest.detector.dao.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BotRepository {
    private static Logger log = LoggerFactory.getLogger(BotRepository.class);
    private static DataSourcePostgreSQL dataSource = new DataSourcePostgreSQL();

    public String getLastTicket() {
        Ticket ticket = null;

        String users_query = "SELECT tickets.id, tickets.number, tickets.region, tickets.forest_user, tickets.start_date, " +
                "tickets.finish_date, tickets.forestry, tickets.cutting_type, tickets.ticket_status," +
                " tickets.cutting_status FROM tickets ";

        try {
            Statement statement = dataSource.getConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(users_query);
            log.info("-> Connection complite");
            {
                if (resultSet.next()) {
                    ticket = new Ticket();
                    ticket.setNumber(resultSet.getString("number"));
                    ticket.setRegion(resultSet.getString("region"));
                    ticket.setForestUser(resultSet.getString("forest_user"));
                    ticket.setStartDate(resultSet.getString("start_date"));
                    ticket.setFinishDate(resultSet.getString("finish_date"));
                    ticket.setForestry(resultSet.getString("forestry"));
                    ticket.setCuttingType(resultSet.getString("cutting_type"));
                    ticket.setTicketStatus(resultSet.getString("ticket_status"));
                    ticket.setCuttingStatus(resultSet.getString("cutting_status"));
                }

            }
            System.out.println(ticket);
        } catch (SQLException throwables) {
            log.error(throwables.getMessage());
        }
            return ticket.toString();
    }
}
