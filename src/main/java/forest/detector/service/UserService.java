package forest.detector.service;

import forest.detector.dao.entity.User;
import forest.detector.dao.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository repo;

    public UserService(javax.sql.DataSource dataSource) {
        this.repo = new UserRepository(dataSource);
    }

    public List<User> getUsers(){
        return repo.getUsers();
    }

    public boolean checkUser(String username, String passwd){
        return repo.checkUser(username, passwd);
    }

    public String authenticateUser(String mail, String pass){
        return repo.authenticateUser(mail,pass);
    }

    public void setUserInDB(String email, String password, String first_name, String last_name, String avatar){
        repo.setUserInDB(email, password, first_name, last_name, avatar);
    }

    public void adminSetUserInDB(String email, String password, String first_name, String last_name, String avatar, String role){
        repo.adminSetUserInDB(email,password,first_name,last_name, avatar, role);
    }

    public User getUserByEmail(String email){
        return repo.getUserByEmail(email);
    }

    public void updateUserInDB(String email, String password, String first_name, String last_name, String avatar, String role){
        repo.adminUpdateUserInDB(email, password, first_name, last_name, avatar, role);
    }

    public void settingsUpdateUserInDB(String email, String new_password, String avatar){
        repo.settingsUpdateUserInDB(email, new_password, avatar);
    }

    public void deleteUser(String email){
        repo.deleteUser(email);
    }
}
