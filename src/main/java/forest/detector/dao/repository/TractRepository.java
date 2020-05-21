package forest.detector.dao.repository;

import forest.detector.dao.entity.Tract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TractRepository {
    private final javax.sql.DataSource dataSource;
    private static Logger log = LoggerFactory.getLogger(TractRepository.class);

    public TractRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Tract tract) {
        String query = "INSERT INTO tracts(ticket_number, quarter, division, range, area, " +
                "forest_type, general_allowed_extent, allowed_extent, cutting_status, contributor, map_id)" +
                "VALUES('?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?');";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tract.getTicketNumber());
            preparedStatement.setString(2, tract.getQuarter());
            preparedStatement.setString(3, tract.getDivision());
            preparedStatement.setString(4, tract.getRange());
            preparedStatement.setFloat(5, tract.getArea());
            preparedStatement.setString(6, tract.getForestType());
            preparedStatement.setFloat(7, tract.getGeneralAllowedExtent());
            preparedStatement.setFloat(8, tract.getAllowedExtent());
            preparedStatement.setString(9, tract.getCuttingStatus());
            preparedStatement.setString(10, tract.getContributor());
            preparedStatement.setString(11, tract.getMapId());
//            preparedStatement.addBatch();
//            preparedStatement.executeBatch();
            preparedStatement.execute();
            log.info(" added tract");
        } catch (SQLException ex) {
            log.error("FAILED adding tract: ");
        } finally {
        }
    }
}
