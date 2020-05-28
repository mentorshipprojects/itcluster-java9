package forest.detector.dao.repository;

import forest.detector.dao.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;

public class TicketRepository {
    private final DataSource dataSource;
    private static Logger log = LoggerFactory.getLogger(TicketRepository.class);
    private static int ticketID;

    public TicketRepository(javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int save(Ticket ticket) {

        String query = "INSERT INTO tickets(number, region, forest_user, start_date, finish_date, " +
                "forestry, cutting_type, ticket_status, cutting_status)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, ticket.getNumber());
            preparedStatement.setString(2, ticket.getRegion());
            preparedStatement.setString(3, ticket.getForestUser());
            preparedStatement.setDate(4, ticket.getStartDate());
            preparedStatement.setDate(5, ticket.getFinishDate());
            preparedStatement.setString(6, ticket.getForestry());
            preparedStatement.setString(7, ticket.getCuttingType());
            preparedStatement.setString(8, ticket.getTicketStatus());
            preparedStatement.setString(9, ticket.getCuttingStatus());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                ticketID = resultSet.getInt(1);
            }
            log.info("ADDED  ● ticket " + ticket.getNumber());
            return ticketID;
        } catch (SQLException e) {
            log.error("ADDING TICKET FAILED " + ticket.getNumber(), e);
            return ticketID;
        }
    }

    public Ticket check(Ticket ticket) {
        Ticket checkedTicket = new Ticket();
        String query = "SELECT id, region, forest_user, forestry, cutting_type, ticket_status, cutting_status " +
                "FROM tickets WHERE number=? AND start_date=? AND finish_date=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ticket.getNumber());
            preparedStatement.setDate(2, ticket.getStartDate());
            preparedStatement.setDate(3, ticket.getFinishDate());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                log.info("EXISTS ● ticket " + ticket.getNumber());
                checkedTicket.setId(resultSet.getInt("id"));
                checkedTicket.setRegion(resultSet.getString("region"));
                checkedTicket.setForestUser(resultSet.getString("forest_user"));
                checkedTicket.setForestry(resultSet.getString("forestry"));
                checkedTicket.setCuttingType(resultSet.getString("cutting_type"));
                checkedTicket.setTicketStatus(resultSet.getString("ticket_status"));
                checkedTicket.setCuttingStatus(resultSet.getString("cutting_status"));
                checkedTicket.setNumber(ticket.getNumber());
                checkedTicket.setStartDate(ticket.getStartDate());
                checkedTicket.setFinishDate(ticket.getFinishDate());
            } else {
                checkedTicket.setId(-1);
            }
            return checkedTicket;
        } catch (SQLException e) {
            log.error("CHECKING TICKET FAILED " + ticket.getNumber(), e);
            checkedTicket.setId(-2);
            return checkedTicket;
        }
    }

    public void update(Ticket ticket) {
        String query = "UPDATE tickets SET region=?, forest_user=?, forestry=?, " +
                "cutting_type=?, ticket_status=?, cutting_status=? " +
                "WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, ticket.getRegion());
            preparedStatement.setString(2, ticket.getForestUser());
            preparedStatement.setString(3, ticket.getForestry());
            preparedStatement.setString(4, ticket.getCuttingType());
            preparedStatement.setString(5, ticket.getTicketStatus());
            preparedStatement.setString(6, ticket.getCuttingStatus());
            preparedStatement.setInt(7, ticket.getId());
            preparedStatement.executeUpdate();
            log.info("UPDATED ");
        } catch (SQLException e) {
            log.error("UPDATING TICKET FAILED " + ticket.getNumber(), e);
        }
    }
}