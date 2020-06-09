package forest.detector.dao.repository;

import forest.detector.dao.entity.Ticket;
import forest.detector.dao.entity.Tract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TractRepository {
    private final javax.sql.DataSource dataSource;
    private static Logger log = LoggerFactory.getLogger(TractRepository.class);

    public TractRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Tract> getTracts(int ticketID) {
        List<Tract> list = new ArrayList<>();
        String query = "select * from tracts WHERE ticket_id=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Tract tract = new Tract();
                tract.setQuarter(rs.getString("quarter"));
                tract.setDivision(rs.getString("division"));
                tract.setRange(rs.getString("range"));
                tract.setArea(rs.getFloat("area"));
                tract.setForestType(rs.getString("forest_type"));
                tract.setGeneralAllowedExtent(rs.getInt("general_allowed_extent"));
                tract.setContributor(rs.getString("contributor"));
                list.add(tract);
            }
        } catch (Exception e) {
            log.error("Can't get tracts.", e);
        }
        return list;
    }

    public void save(Tract tract) {
        String query = "INSERT INTO tracts(ticket_id, quarter, division, range, area, " +
                "forest_type, general_allowed_extent, allowed_extent, cutting_status, contributor, map_id)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, tract.getTicketID());
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
            preparedStatement.execute();
            log.info(" ADDED  ○ tract  " + tract.getQuarter() + "/" + tract.getDivision() + "/" + tract.getRange());
        } catch (SQLException e) {
            log.error(" FAILED ADDING TRACT ", e);
        }
    }

    public Tract check(Tract tract) {
        Tract checkedTract = new Tract();
        String query = "SELECT ticket_id, quarter, division, range, area, forest_type, " +
                "general_allowed_extent, allowed_extent, cutting_status, contributor, map_id " +
                "FROM tracts WHERE map_id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, tract.getMapId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                log.info(" EXISTS ○ tract  " + tract.getQuarter() + "/" + tract.getDivision() + "/" + tract.getRange());
                checkedTract.setTicketID(resultSet.getInt("ticket_id"));
                checkedTract.setQuarter(resultSet.getString("quarter"));
                checkedTract.setDivision(resultSet.getString("division"));
                checkedTract.setRange(resultSet.getString("range"));
                checkedTract.setArea(resultSet.getFloat("area"));
                checkedTract.setForestType(resultSet.getString("forest_type"));
                checkedTract.setGeneralAllowedExtent(resultSet.getFloat("general_allowed_extent"));
                checkedTract.setAllowedExtent(resultSet.getFloat("allowed_extent"));
                checkedTract.setCuttingStatus(resultSet.getString("cutting_status"));
                checkedTract.setContributor(resultSet.getString("contributor"));
                checkedTract.setMapId(resultSet.getString("map_id"));
            } else {
                checkedTract.setId(-1);
            }
            return checkedTract;
        } catch (SQLException e) {
            log.error(" FAILED CHECKING TRACT ", e);
            checkedTract.setId(-2);
            return checkedTract;
        }
    }

    public void update(Tract tract) {
        String query = "UPDATE tracts SET ticket_id=?, quarter=?, division=?, range=?, " +
                "area=?, forest_type=?, general_allowed_extent=?, allowed_extent=?, " +
                "cutting_status=?, contributor=? " +
                "WHERE map_id=?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, tract.getTicketID());
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
            preparedStatement.executeUpdate();
            log.info(" UPDATED ");
        } catch (SQLException e) {
            log.error(" UPDATING TICKET FAILED " + tract.getId(), e);
        }
    }
}
