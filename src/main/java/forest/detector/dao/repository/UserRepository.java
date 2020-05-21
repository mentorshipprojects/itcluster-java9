package forest.detector.dao.repository;

import forest.detector.dao.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserRepository {

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> getUsers() {
        try {
            // test connection here
            Connection connection = dataSource.getConnection();
            connection.close();
        } catch (Exception e) {

        }
        return List.of(new User());
    }

    public boolean checkUser(String username, String passwd) {
        boolean st = false;
        try (Connection con = dataSource.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from users where user_name=? and user_passwd=?");
            ps.setString(1, username);
            ps.setString(2, passwd);
            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }
}
