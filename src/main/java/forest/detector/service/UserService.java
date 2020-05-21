package forest.detector.service;


import forest.detector.dao.entity.User;
import forest.detector.dao.repository.UserRepository;

import javax.sql.DataSource;
import java.util.List;

public class UserService {

    private final UserRepository repo;

    public UserService(DataSource dataSource) {
        this.repo = new UserRepository(dataSource);
    }

    public List<User> getUsers(){
        return repo.getUsers();
    }

    public boolean checkUser(String username, String passwd){
        return repo.checkUser(username, passwd);
    }
}
