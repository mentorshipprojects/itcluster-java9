package forest.detector.dao.repository;

import forest.detector.dao.entity.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    private final DataSource dataSource;
    private static Logger log = LoggerFactory.getLogger(TicketRepository.class);
    private static int ticketID;
    private static int statusID;

    public TicketRepository(javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Ticket> getTickets() {
        List<Ticket> list = new ArrayList<>();

        try(Connection con = dataSource.getConnection()) {
            // test connection here
            PreparedStatement ps = con.prepareStatement("select * from tickets LIMIT 50");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Ticket(rs.getInt("id"),
                        rs.getString("number"),
                        rs.getString("region"),
                        rs.getString("forest_user"),
                        rs.getDate("start_date"),
                        rs.getDate("finish_date"),
                        rs.getString("forestry"),
                        rs.getString("cutting_type"),
                        rs.getString("ticket_status"),
                        rs.getString("cutting_status")));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
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

    public int statusTotalRecord(int total) {
        String query = "INSERT INTO update_status(total, finished) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, total);
            preparedStatement.setBoolean(2, false);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                statusID = resultSet.getInt(1);
            }
            log.info("UPDATED TOTAL", statusID);
        } catch (SQLException e) {
            log.error("UPDATING TOTAL FAILED ", e);
        }
        return statusID;
    }

    public void statusProgressRecord(int progress, boolean isFinished, int statusID) {
        String query = "UPDATE update_status SET progress=?, finished=? WHERE id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, progress);
            preparedStatement.setBoolean(2, isFinished);
            preparedStatement.setInt(3, statusID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("UPDATING PROGRESS FAILED ", e);
        }
    }

    public int[] statusUpload() {
        String query = "SELECT * FROM update_status ORDER BY ID DESC LIMIT 1";
        int[] i = {3, 3, 3};
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                i[0] = resultSet.getInt("progress");
                i[1] = resultSet.getInt("total");
                if (resultSet.getBoolean("finished")) {
                    i[2] = 1;
                } else {
                    i[2] = 0;
                }
            }
            return i;
        } catch (SQLException e) {
            log.error("STATUS_UPLOAD FAILED ", e);
        }
        return i;
    }
}