//package forest.detector.bot;
//
//import forest.detector.dao.entity.Ticket;
//import forest.detector.dao.repository.TicketRepository;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class BotDB {
//    private final DataSource dataSource;
//    TicketRepository ticketRepository = new TicketRepository(dataSource);
//
//    public BotDB(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
////    public Ticket getTicket() {
////        Ticket ticket = null;
////
////        String users_query = "SELECT ticket.id, ticket.number, ticket.region, ticket.forest_user, ticket.start_date, " +
////                "ticket.finish_date, ticket.forestry, ticket.cutting_type, ticket.ticket_status," +
////                " ticket.cutting_status FROM tikets ";
////
////        try (
////                Connection connection = dataSource.getConnection();
////                Statement statement = connection.createStatement();
////                ResultSet resultSet = statement.executeQuery(users_query);
////        ) {
////            if (resultSet.next()) {
////                ticket = new Ticket();
////                ticket.setNumber(resultSet.getString("number"));
////                ticket.setRegion(resultSet.getString("region"));
////                ticket.setForestUser(resultSet.getString("forest_user"));
////                ticket.setStartDate(resultSet.getString("start_date"));
////                ticket.setFinishDate(resultSet.getString("finish_date"));
////                ticket.setForestry(resultSet.getString("forestry"));
////                ticket.setCuttingType(resultSet.getString("cutting_type"));
////                ticket.setTicketStatus(resultSet.getString("ticket_status"));
////                ticket.setCuttingStatus(resultSet.getString("cutting_status"));
////            }
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return ticket;
////    }
//}