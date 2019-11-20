package springbootcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @PostMapping(value = "/user/login")
    public String Login(@RequestParam("userName") String userName, @RequestParam("password") String password, Map<String, Object> map,
                        HttpSession session) {
        if (!StringUtils.isEmpty(userName) && "123456".equals(password)) {
            session.setAttribute("loginUser", userName);
            return "redirect:/main.html";
        } else {
            map.put("message", "用户名或密码错误");
            return "login";
        }
    }
}
