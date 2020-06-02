//package forest.detector.dao.repository;
//
//import forest.detector.dao.entity.Ticket;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class TicketRepository {
//    private static Logger log = LoggerFactory.getLogger(TicketRepository.class);
//
//    public boolean addTicket(Ticket ticket) {
//        DataSource dataSource = new DataSource();
//        String query = "INSERT INTO public.tickets(number, region, forest_user, start_date, finish_date, " +
//                "forestry, cutting_type, ticket_status, cutting_status)" +
//                "VALUES('" + ticket.getNumber() + "', '" + ticket.getRegion() + "', '" + ticket.getForestUser() + "', '" +
//                "" + ticket.getStartDate() + "', '" + ticket.getFinishDate() + "', '" + ticket.getForestry() + "', '" +
//                "" + ticket.getCuttingType() + "', '" + ticket.getTicketStatus() + "', '" + ticket.getCuttingStatus() + "');";
//        try {
//            Connection connection = dataSource.getConnection();
//            Statement statement = connection.createStatement();
//            statement.executeUpdate(query);
//            log.info("added ticket " + ticket.getNumber());
//            return true;
//        } catch (SQLException e) {
//            log.error("FAILED adding ticket " + ticket.getNumber());
//            return false;
//        }
//    }
//}
