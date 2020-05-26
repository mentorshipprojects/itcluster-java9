package forest.detector.dao.repository;

import forest.detector.dao.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;

public class TicketRepository {
    private final DataSource dataSource;
    private static Logger log = LoggerFactory.getLogger(TicketRepository.class);
    private Date date = new Date(System.currentTimeMillis());

    public TicketRepository(javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean save(Ticket ticket) {

        String query = "INSERT INTO tickets(number, region, forest_user, start_date, finish_date, " +
                "forestry, cutting_type, ticket_status, cutting_status)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ticket.getNumber());
            preparedStatement.setString(2, ticket.getRegion());
            preparedStatement.setString(3, ticket.getForestUser());
            preparedStatement.setDate(4, ticket.getStartDate());
            preparedStatement.setDate(5, ticket.getFinishDate());
            preparedStatement.setString(6, ticket.getForestry());
            preparedStatement.setString(7, ticket.getCuttingType());
            preparedStatement.setString(8, ticket.getTicketStatus());
            preparedStatement.setString(9, ticket.getCuttingStatus());
//            preparedStatement.addBatch();
//            preparedStatement.executeBatch();
            preparedStatement.execute();
            log.info("added ticket " + ticket.getNumber());
            return true;

        } catch (SQLException e) {
            log.error("FAILED adding ticket " + ticket.getNumber(), e);
            return false;
        }
    }

    public boolean isInDataBase(Ticket ticket) {

        String query = "SELECT number, start_date, Finish_date FROM tickets " +
                "WHERE number=? AND start_date=? AND finish_date=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ticket.getNumber());
            preparedStatement.setDate(2, ticket.getStartDate());
            preparedStatement.setDate(3, ticket.getFinishDate());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                log.info("ticket already exists " + ticket.getNumber());
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            log.error("FAILED checking ticket " + ticket.getNumber(), e);
            return true;
        }
    }
}
