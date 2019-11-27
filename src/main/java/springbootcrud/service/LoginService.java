package springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootcrud.dao.LoginMapper;
import springbootcrud.dto.UserRegister;

@Service
public class LoginService {
    @Autowired
    LoginMapper loginMapper;

    public void registerUser(UserRegister userRegister) {
        loginMapper.registerUser(userRegister);
    }

    public UserRegister login(UserRegister user) {
        return loginMapper.login(user);
    }
}
