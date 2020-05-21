package forest.detector.dao.repository;

import forest.detector.dao.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketRepository {
    private final DataSource dataSource;
    private static Logger log = LoggerFactory.getLogger(TicketRepository.class);

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
            preparedStatement.setString(4, ticket.getStartDate());
            preparedStatement.setString(5, ticket.getFinishDate());
            preparedStatement.setString(6, ticket.getForestry());
            preparedStatement.setString(7, ticket.getCuttingType());
            preparedStatement.setString(8, ticket.getTicketStatus());
            preparedStatement.setString(9, ticket.getCuttingStatus());
//            preparedStatement.addBatch();
//            preparedStatement.executeBatch();
            log.info("added ticket " + ticket.getNumber());
            //System.out.println(preparedStatement.toString());
            return preparedStatement.execute();

        } catch (SQLException e) {
            log.error("FAILED adding ticket " + ticket.getNumber(), e);
            return false;
        } finally {
        }
    }
}
