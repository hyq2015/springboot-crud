package springbootcrud.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springbootcrud.bean.User;
import springbootcrud.bean.UserAddModel;
import springbootcrud.common.Result;
import springbootcrud.common.ResultUtil;
import springbootcrud.service.UserService;

import java.util.List;

@Api(value = "用户模块", tags = "这是用户模块")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "",httpMethod = "GET")
    @GetMapping(value = "/users", produces = {"application/json"})
    public List<User> getAllUser() {
        return userService.getUsers();
    }

    @ApiOperation(value = "添加用户", httpMethod = "POST", notes = "根据传入参数添加用户，并在添加完成后返回")
    @PostMapping(value = "/addUser", produces = {"application/json"})
    public Result addUser(@RequestBody User user) {
        UserAddModel addUser = new UserAddModel();
        addUser.setAge(user.getAge());
        addUser.setName(user.getName());

        userService.addUser(addUser);
        return ResultUtil.success(addUser);
    }
}
