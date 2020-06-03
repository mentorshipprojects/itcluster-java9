package forest.detector.dao.repository;

import forest.detector.controller.Login;
import forest.detector.dao.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final DataSource dataSource;
    private static Logger log = LoggerFactory.getLogger(UserRepository.class);

    public UserRepository(javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User getUserByEmail(String email) {
        User user = null;

        String users_query = "select * from users join user_roles on users.email=user_roles.email where users.email='"+email+"'";

        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(users_query);
        ) {
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("role_name")
                        );
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return user;
    }

    public void setUserInDB(String email, String password, String first_name, String last_name, String avatar){

        String role = "user";

        try(Connection connection = dataSource.getConnection())
        {
            PreparedStatement ps_users = connection.prepareStatement("insert into users(email, password, first_name, last_name, avatar) values(?,?,?,?,?) ");
            PreparedStatement ps_role = connection.prepareStatement("insert into user_roles(email, role_name) values (?,?)");
            ps_users.setString(1, email);
            ps_users.setString(2, password);
            ps_users.setString(3, first_name);
            ps_users.setString(4, last_name);
            ps_users.setString(5, avatar);

            ps_role.setString(1, email);
            ps_role.setString(2, role);

            ps_users.executeUpdate();
            ps_role.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public void adminSetUserInDB(String email, String password, String first_name, String last_name, String avatar, String role){

        try(Connection connection = dataSource.getConnection())
        {
            PreparedStatement ps_users = connection.prepareStatement("insert into users(email, password, first_name, last_name, avatar) values(?,?,?,?,?) ");
            PreparedStatement ps_role = connection.prepareStatement("insert into user_roles(email, role_name) values (?,?)");
            ps_users.setString(1, email);
            ps_users.setString(2, password);
            ps_users.setString(3, first_name);
            ps_users.setString(4, last_name);
            ps_users.setString(5, avatar);

            ps_role.setString(1, email);
            ps_role.setString(2, role);

            ps_users.executeUpdate();
            ps_role.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public void adminUpdateUserInDB(String email, String password, String first_name, String last_name, String avatar, String role){

        try(Connection connection = dataSource.getConnection();)
        {

            PreparedStatement ps_users = connection.prepareStatement("update users set password=?, first_name=?, last_name=?, avatar=? where email=?");
            PreparedStatement ps_role = connection.prepareStatement("update user_roles set role_name=? where email=?");

            ps_users.setString(1, password);
            ps_users.setString(2, first_name);
            ps_users.setString(3, last_name);
            ps_users.setString(4, avatar);
            ps_users.setString(5, email);

            ps_role.setString(1, role);
            ps_role.setString(2, email);

            ps_users.executeUpdate();
            ps_role.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public void settingsUpdateUserInDB(String email, String new_password, String avatar){

        try(Connection connection = dataSource.getConnection();)
        {
            PreparedStatement ps_users = connection.prepareStatement("update users set password=?, avatar=? where email=?");

            ps_users.setString(1, new_password);
            ps_users.setString(2, avatar);
            ps_users.setString(3, email);
            ps_users.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public String authenticateUser(String mail, String pass){

        String userNameDB = "";
        String roleDB = "";
        String passwordDB = "";

        try(Connection con = dataSource.getConnection())
        {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users join user_roles on users.email=user_roles.email");

            while(resultSet.next())
            {
                userNameDB = resultSet.getString("email");
                roleDB = resultSet.getString("role_name");
                passwordDB = resultSet.getString("password");

                if(mail.equals(userNameDB) && roleDB.equals("admin") && passwordDB.equals(pass))
                    return "Admin_Role";
                else if(mail.equals(userNameDB) && roleDB.equals("moderator-api") && passwordDB.equals(pass))
                    return "Moderator-api";
                else if(mail.equals(userNameDB) && roleDB.equals("moderator-gui") && passwordDB.equals(pass))
                    return "Moderator-gui";
                else if(mail.equals(userNameDB) && roleDB.equals("user") && passwordDB.equals(pass))
                    return "User";
            }
        }
        catch(SQLException e)
        {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return "<h3 style=\"color:#FF0000\";>Invalid user</h3>";
    }

    public List<User> getUsers() {
        List<User> list = new ArrayList<>();

        try(Connection con = dataSource.getConnection()) {
            // test connection here
            PreparedStatement ps = con.prepareStatement("select users.email, users.password, users.first_name, users.last_name, user_roles.role_name from users, user_roles where users.email=user_roles.email");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new User(rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("role_name")));
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
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
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return st;
    }

    public void deleteUser(String email){
        try(Connection con = dataSource.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("delete from users where email=? ");
            PreparedStatement ps2 = con.prepareStatement("delete from user_roles where email=? ");
            ps.setString(1, email);
            ps2.setString(1, email);
            //ps.executeQuery();
            ps.executeUpdate();
            ps2.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }


}