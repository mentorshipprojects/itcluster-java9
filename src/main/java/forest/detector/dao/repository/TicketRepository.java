package forest.detector.dao.repository;

import forest.detector.dao.entity.Ticket;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketRepository {

    public void addTicket(Ticket ticket) {
        DataSource dataSource = new DataSource();
        String query = "INSERT INTO forest.tickets(number, region, forest_user, start_date, finish_date, " +
                "forestry, cutting_type, ticket_status, cutting_status)" +
                "VALUES('"+ticket.getNumber()+"', '"+ticket.getRegion()+"', '"+ticket.getForestUser()+"', '" +
                ""+ticket.getStartDate()+"', '"+ticket.getFinishDate()+"', '"+ticket.getForestry()+"', '"+
                ""+ticket.getCuttingType()+"', '"+ticket.getTicketStatus()+"', '"+ticket.getCuttingStatus()+"');";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("added ticket "+ticket.getNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
