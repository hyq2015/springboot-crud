package springbootcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import springbootcrud.common.HttpResult;
import springbootcrud.common.HttpStatus;
import springbootcrud.common.RandomCode;
import springbootcrud.dao.LoginMapper;
import springbootcrud.dto.UserEmail;
import springbootcrud.dto.UserRegister;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private RedisTemplate<Object, UserRegister> userRegisterRedisTemplate;

    public UserRegister login(UserRegister user) {
        UserRegister redisUser = (UserRegister)redisTemplate.opsForValue().get(user.getEmail());
        if (redisUser != null) {
            return redisUser;
        } else {
            redisUser = loginMapper.login(user);
            redisTemplate.opsForValue().set(user.getEmail(),redisUser);
            return redisUser;
        }
    }

    // 给邮箱发送验证码
    private void sendVerifyCode(UserEmail userEmail) {
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

    // 用户注册
    private void registerUser(UserRegister userRegister) {
        loginMapper.registerUser(userRegister);
    }

    // 验证此邮箱是否已经注册过, true为注册过，false为未注册
    public Boolean isEmailRegistered(UserEmail userEmail) {
        UserRegister userRegister = (UserRegister)userRegisterRedisTemplate.opsForValue().get(userEmail.getEmail());
        if (userRegister == null) {
            List<UserRegister> list = loginMapper.validateEmail(userEmail);
            if (list.size() > 0) {
                userRegister = list.get(0);
                // 把查询出来的数据放入Redis
                userRegisterRedisTemplate.opsForValue().set(userEmail.getEmail(), userRegister);
            }
        }
        return userRegister != null;
    }

    // 验证是否已经给此邮箱发送过验证码
    private List<UserEmail> getSpecifiedEmail(UserEmail userEmail) {
        return loginMapper.getSpecifiedEmail(userEmail);
    }
    public void checkIfSendSmsCode(UserEmail userEmail) {
        List<UserEmail> list = getSpecifiedEmail(userEmail);
        if (list.size() == 0) {
            sendVerifyCode(userEmail);
        } else {
            if (isSmsCodeExpired(list.get(0).getCreateTime())) {
                sendVerifyCode(userEmail);
            }
        }
    }
    public HttpResult shouldUserRegister(UserEmail userEmail, UserRegister userRegister) {
        List<UserEmail> list = getSpecifiedEmail(userEmail);
        HttpResult httpResult = new HttpResult();
        if (list.size() == 0) {
            httpResult.setType("error");
            httpResult.setStatus(HttpStatus.SMS_CODE_INVALID);
        } else if (isSmsCodeExpired(list.get(0).getCreateTime()) && list.get(0).getCode().equals(userRegister.getSmsCode())) {
            httpResult.setType("error");
            httpResult.setStatus(HttpStatus.SMS_CODE_EXPIRED);
        }else if (isSmsCodeExpired(list.get(0).getCreateTime()) && !list.get(0).getCode().equals(userRegister.getSmsCode())) {
            httpResult.setType("error");
            httpResult.setStatus(HttpStatus.SMS_CODE_INVALID);
        } else if (!isSmsCodeExpired(list.get(0).getCreateTime()) && !list.get(0).getCode().equals(userRegister.getSmsCode())) {
            httpResult.setType("error");
            httpResult.setStatus(HttpStatus.SMS_CODE_MISMATCH);
        } else {
            registerUser(userRegister);
            httpResult.setType("success");
            httpResult.setStatus(HttpStatus.REGISTER_SUCCESS);
        }
        return httpResult;
    }

    // 验证是否过期
    private Boolean isSmsCodeExpired(Long createTime) {
        Long t = System.currentTimeMillis();
        int expiredMin = 1;
        // true：过期  false：有效
        return (t - createTime) >= (expiredMin * 60 * 1000);
    }
}
