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

    public void setUserInDB(String email, String password, String first_name, String last_name){

        String role = "user";

        try(Connection connection = dataSource.getConnection())
        {
            String users_query = "INSERT INTO users(email, password, first_name, last_name)" +
                    "VALUES ('" + email + "', '" + password + "', '" + first_name + "', '" + last_name + "');";

            String role_query = "INSERT INTO user_roles(email, role_name)" +
                    "VALUES ('" + email + "', '" + role + "');";

            Statement statement = connection.createStatement();
            statement.executeUpdate(users_query);
            statement.executeUpdate(role_query);

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public void adminSetUserInDB(String email, String password, String first_name, String last_name, String avatar, String role){

        try(Connection connection = dataSource.getConnection())
        {
            String users_query = "INSERT INTO users(email, password, first_name, last_name, avatar)" +
                    "VALUES ('" + email + "', '" + password + "', '" + first_name + "', '" + last_name + "', '"+avatar+"');";

            String role_query = "INSERT INTO user_roles(email, role_name)" +
                    "VALUES ('" + email + "', '" + role + "');";

            Statement statement = connection.createStatement();
            statement.executeUpdate(users_query);
            statement.executeUpdate(role_query);

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public void updateUserRoleInDB(String email, String password, String first_name, String last_name, String avatar, String role){

        try(Connection con = dataSource.getConnection();)
        {
            String userQuery = "update users " +
                    "set password='"+password+"', first_name='"+first_name+"', last_name='"+last_name+"', avatar='"+avatar+"' " +
                    "where email='"+email+"' ";

           String roleQuery = "update user_roles set role_name='"+role+"' where email='"+email+"' ";
//
            Statement statement = con.createStatement();
            statement.executeUpdate(userQuery);
            statement.executeUpdate(roleQuery);

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
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
            PreparedStatement ps = con.prepareStatement("delete from users, user_roles where users.email=?, user_roles.email=? ");
            ps.setString(1, email);
            ps.setString(2, email);
            ps.executeQuery();

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }


}