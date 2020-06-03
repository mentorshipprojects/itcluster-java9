package forest.detector.dao.repository;

import forest.detector.dao.entity.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsRepository {
    private final DataSource dataSource;
    private static Logger log = LoggerFactory.getLogger(AnalyticsRepository.class);

    public AnalyticsRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // статистика відношень площа/об'єм за переліком типів вирубки
    public List<Stat> statCuttingType(int year) {
        List<Stat> list = new ArrayList<Stat>();
        String query = "SELECT SUM(tracts.general_allowed_extent), SUM(tracts.area), tickets.cutting_type " +
                " FROM tracts JOIN tickets ON tracts.ticket_id=tickets.id " +
                "WHERE tickets.start_date BETWEEN ? AND ? " +
                "GROUP BY tickets.cutting_type " +
                "ORDER BY SUM(tracts.general_allowed_extent) DESC";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(year + "-01-01"));
            preparedStatement.setDate(2, java.sql.Date.valueOf(year + "-12-31"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Stat stat = new Stat();
                float x = (float) (Math.round(resultSet.getFloat(2) * 100.0) / 100.0);
                stat.setGeneralAllowedExtent(resultSet.getLong(1));
                stat.setArea(x);
                stat.setStatName(resultSet.getString(3));
                list.add(stat);
            }
            log.info("statCuttingType request SUCCEEDED ");
        } catch (SQLException e) {
            log.error("statCuttingType request FAILED ", e);
        }
        return list;
    }

    // статистика відношень площа/об'єм за переліком лісових господарств
    public List<Stat> statForestUser(int year) {
        List<Stat> list = new ArrayList<Stat>();
        String query = "SELECT SUM(tracts.general_allowed_extent), SUM(tracts.area), tickets.forest_user " +
                " FROM tracts JOIN tickets ON tracts.ticket_id=tickets.id " +
                "WHERE tickets.start_date BETWEEN ? AND ? " +
                "GROUP BY tickets.forest_user " +
                "ORDER BY SUM(tracts.general_allowed_extent) DESC";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(year + "-01-01"));
            preparedStatement.setDate(2, java.sql.Date.valueOf(year + "-12-31"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Stat stat = new Stat();
                float x = (float) (Math.round(resultSet.getFloat(2) * 100.0) / 100.0);
                stat.setGeneralAllowedExtent(resultSet.getLong(1));
                stat.setArea(x);
                stat.setStatName(resultSet.getString(3));
                list.add(stat);
            }
            log.info("statForestUser request SUCCEEDED ");
        } catch (SQLException e) {
            log.error("statForestUser request FAILED ", e);
        }
        return list;
    }

    // статистика відношень площа/об'єм за переліком лісництв
    public List<Stat> statForestry(int year) {
        List<Stat> list = new ArrayList<Stat>();
        String query = "SELECT SUM(tracts.general_allowed_extent), SUM(tracts.area), tickets.forestry " +
                " FROM tracts JOIN tickets ON tracts.ticket_id=tickets.id " +
                "WHERE tickets.start_date BETWEEN ? AND ? " +
                "GROUP BY tickets.forestry " +
                "ORDER BY SUM(tracts.general_allowed_extent) DESC";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(year + "-01-01"));
            preparedStatement.setDate(2, java.sql.Date.valueOf(year + "-12-31"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Stat stat = new Stat();
                float x = (float) (Math.round(resultSet.getFloat(2) * 100.0) / 100.0);
                stat.setGeneralAllowedExtent(resultSet.getLong(1));
                stat.setArea(x);
                stat.setStatName(resultSet.getString(3));
                list.add(stat);
            }
            log.info("statForestry request SUCCEEDED ");
        } catch (SQLException e) {
            log.error("statForestry request FAILED ", e);
        }
        return list;
    }

    // список лісових господарств
    public List<String> listOfForestUser() {
        List<String> list = new ArrayList<String>();
        String query = "SELECT DISTINCT forest_user FROM tickets ORDER BY forest_user";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            log.info("listOfForestUser request SUCCEEDED ");
        } catch (SQLException e) {
            log.error("listOfForestUser request FAILED ", e);
        }
        return list;
    }

    //список лісництв (параметр – лісове господарство)
    public List<String> listOfForestry(String forestUser) {
        List<String> list = new ArrayList<String>();
        String query = "SELECT DISTINCT forestry FROM tickets WHERE forest_user=? ORDER BY forestry";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, forestUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            log.info("listOfForestry request SUCCEEDED ");
        } catch (SQLException e) {
            log.error("listOfForestry request FAILED ", e);
        }
        return list;
    }
}
