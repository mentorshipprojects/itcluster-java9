package forest.detector.dao.repository;

import forest.detector.dao.entity.Tract;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TractRepository {
    public void addTract(Tract tract) {
        DataSource dataSource = new DataSource();
        String query = "INSERT INTO forest.tracts(ticket_number, quarter, division, `range`, area, " +
                "forest_type, general_allowed_extent, allowed_extent, cutting_status, contributor, map_id)" +
                "VALUES('"+tract.getTicketNumber()+"', '"+tract.getQuarter()+"', '"+tract.getDivision()+"', '" +
                ""+tract.getRange()+"', '"+tract.getArea()+"', '"+tract.getForestType()+"', '"+
                ""+tract.getGeneralAllowedExtent()+"', '"+tract.getAllowedExtent()+"', '"+tract.getCuttingStatus()+"', '"+
                ""+tract.getContributor()+"', '"+tract.getMapId()+"');";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(" • added tract ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
