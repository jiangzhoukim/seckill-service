package cn.jiang.service;

import cn.jiang.mode.User;
import cn.jiang.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService extends IService<User> {

    void addUser(User user);

    User getUser(Long id);

    Result<User> login(User User);

    Result<List<User>> getAll();

    Result update(User user);

    void witeExcel(HttpServletResponse response);
}
