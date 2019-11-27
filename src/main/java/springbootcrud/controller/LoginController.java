package springbootcrud.controller;

import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import springbootcrud.bean.User;
import springbootcrud.common.Result;
import springbootcrud.common.ResultUtil;
import springbootcrud.config.JwtConfig;
import springbootcrud.dto.UserRegister;
import springbootcrud.service.LoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    JwtConfig jwtConfig;

    @ApiOperation(value = "",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string")
    })
    @PostMapping(value = "/login")
    public Result Login(@RequestBody UserRegister user,
                        HttpSession session,
                        HttpServletResponse response) {
        UserRegister loginUser = loginService.login(user);
        if (loginUser != null) {
            Map<String,Object> map = new HashMap<>();
            map.put("userName", loginUser.getUserName());
            map.put("email", loginUser.getEmail());
            String token = jwtConfig.getToken(loginUser.getUserName()+loginUser.getEmail()) ;
            map.put("token", token);
            return ResultUtil.success(map);
        } else {
            return ResultUtil.error(1, "user not found");
        }
    }
    @PostMapping(value = "/register")
    public Result Register(@RequestBody UserRegister userRegister) {
        loginService.registerUser(userRegister);
        return ResultUtil.success(null);
    }

    @GetMapping(value = "/token")
    public Result Token(HttpServletRequest request) {
        String token1 = request.getHeader("token");
        System.out.println("token:====" + token1);
        return ResultUtil.success();
    }

}
