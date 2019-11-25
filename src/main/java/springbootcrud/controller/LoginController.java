package springbootcrud.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;
import springbootcrud.bean.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @ApiOperation(value = "",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string")
    })
    @GetMapping(value = "/user/login")
    @ResponseBody
    public String Login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        if (!StringUtils.isEmpty(userName) && "123456".equals(password)) {
//            return "redirect:/main.html";
            return "ok";
        } else {
            return "login";
        }
    }
//    public String Login(@RequestParam("userName") String userName, @RequestParam("password") String password, Map<String, Object> map,
//                        HttpSession session) {
//        if (!StringUtils.isEmpty(userName) && "123456".equals(password)) {
//            session.setAttribute("loginUser", userName);
//            return "redirect:/main.html";
//        } else {
//            map.put("message", "用户名或密码错误");
//            return "login";
//        }
//    }
}
