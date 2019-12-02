package springbootcrud.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springbootcrud.common.*;
import springbootcrud.dto.UserEmail;
import springbootcrud.dto.UserRegister;
import springbootcrud.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @ApiOperation(value = "",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string")
    })
    @PostMapping(value = "/login")
    public Result Login(@RequestBody UserRegister user) throws TokenException {
        UserRegister loginUser = loginService.login(user);
        if (loginUser != null) {
            Map<String,Object> map = new HashMap<>();
            map.put("userName", loginUser.getUserName());
            map.put("email", loginUser.getEmail());
            String token = JwtTokenUtil.createToken("id=" + loginUser.getId(), false) ;
            map.put("token", token);
            return ResultUtil.success(map);
        } else {
            return ResultUtil.error(1, "user not found");
        }
    }
    @PostMapping(value = "/register")
    public Result Register(@Validated @RequestBody UserRegister userRegister, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindResultException(bindingResult);
        }
        UserEmail uEmail = new UserEmail();
        uEmail.setEmail(userRegister.getEmail());
        Boolean flag = loginService.isEmailRegistered(uEmail);
        if (!flag) {
            HttpResult httpResult = loginService.shouldUserRegister(uEmail,userRegister);
            if (httpResult.getType().equals("success")) {
                return ResultUtil.success(null, httpResult.getStatus().getMessage());
            }
            return ResultUtil.error(httpResult.getStatus().getCode(), httpResult.getStatus().getMessage());
        } else {
            return ResultUtil.error(0, "此邮箱已经注册");
        }
    }

    @GetMapping(value = "/token")
    public Result Token(HttpServletRequest request) {
        String token1 = request.getHeader("token");
        System.out.println("token:====" + token1);
        return ResultUtil.success();
    }
    @PostMapping(value = "/validate/email")
    public Result sendVerifyCode(@Validated @RequestBody UserEmail userEmail, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BindResultException(bindingResult);
        }
        Boolean flag = loginService.isEmailRegistered(userEmail);
        if (!flag) {
            loginService.checkIfSendSmsCode(userEmail);
        } else {
            return ResultUtil.error(0, "此邮箱已经注册");
        }
        return ResultUtil.success(null);
    }

}
