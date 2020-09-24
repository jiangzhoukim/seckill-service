package cn.jiang.controller;

import cn.jiang.ciphertext.Fence;
import cn.jiang.configuration.properties.TestProperties;
import cn.jiang.mode.User;
import cn.jiang.result.Result;
import cn.jiang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JiangZhou
 *
 */
@RestController
@RequestMapping("/user")
@Slf4j
@EnableAutoConfiguration
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TestProperties testProperties;

    @GetMapping("/user")
    public Result getStr(@RequestParam("uid") Long uid){
        User user = userService.getUser(uid);
        Result<User> success = Result.success(user);
        User data = success.getData();
        return success;
    }

    @GetMapping("/addUser")
    public Result addUser(@RequestParam("userName") String name,@RequestParam("password") String password,@RequestParam("age") Integer age){
        User user = User.builder()
                .userName(name)
                .password(password)
                .age(age)
                .build();
        List<User> users = new ArrayList<>();
        //密码加密
        user.setPassword(Fence.encryption(password));
        userService.addUser(user);
        return  Result.success();
    }

    @PostMapping(value = "/login" , produces = "application/json; charset=utf-8")
    public Result<User> signIn(@RequestBody User user){
        Result<User> login = userService.login(user);
        login.getDataWithThrow();
        return  login;
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @GetMapping("/witeExcel")
    public void witeExcel(HttpServletResponse response){
        userService.witeExcel(response);
    }

    @GetMapping("/testPro")
    public  Result<List<TestProperties.Item>> testPro(){
        return  Result.success(testProperties.getPayTypeList());
    }
}
