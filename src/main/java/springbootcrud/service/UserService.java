package springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootcrud.bean.User;
import springbootcrud.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> getUsers() {
        return userMapper.getAllUser();
    }
    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
