package springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootcrud.bean.User;
import springbootcrud.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping(value = "/users")
    public List<User> getAllUser() {
        return userService.getUsers();
    }

    @GetMapping(value = "/addUser")
    public void addUser(User user) {
        userService.addUser(user);
    }
}
