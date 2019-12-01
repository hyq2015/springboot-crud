package springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import springbootcrud.common.RandomCode;
import springbootcrud.dao.LoginMapper;
import springbootcrud.dto.UserEmail;
import springbootcrud.dto.UserRegister;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public void registerUser(UserRegister userRegister) {
        loginMapper.registerUser(userRegister);
    }

    public UserRegister login(UserRegister user) {
        return loginMapper.login(user);
    }


    public void sendVerifyCode(UserEmail userEmail) {
        String code = RandomCode.getCode(4);
        userEmail.setCode(code);
        userEmail.setCreateTime(System.currentTimeMillis());
        loginMapper.sendVerifyCode(userEmail);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("验证码-Ricky的实验平台");
        simpleMailMessage.setText("验证码：" + code + "，验证码有效期为1分钟");
        simpleMailMessage.setTo(userEmail.getEmail());
        simpleMailMessage.setFrom("505970021@qq.com");
        mailSender.send(simpleMailMessage);
    }

    public List<UserRegister> validateEmail(UserEmail userEmail) {
        List<UserRegister> userRegisterList = (List<UserRegister>)redisTemplate.opsForValue().get("userRegisterList");
        if (userRegisterList == null) {
            userRegisterList = loginMapper.validateEmail(userEmail);
            // 把查询出来的数据放入Redis
            redisTemplate.opsForValue().set("userRegisterList", userRegisterList);
        }
        return userRegisterList;
    }

    public List<UserEmail> getSpecifiedEmail(UserEmail userEmail) {
        return loginMapper.getSpecifiedEmail(userEmail);
    }
}
