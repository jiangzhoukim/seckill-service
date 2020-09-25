package cn.jiang.service.impl;

import cn.jiang.dao.UserDao;
import cn.jiang.exception.BussException;
import cn.jiang.mode.User;
import cn.jiang.result.Result;
import cn.jiang.result.ResultCodeEnum;
import cn.jiang.service.UserService;
import cn.jiang.util.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceimpl extends ServiceImpl<UserDao,User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.insert(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public Result<User> login(User user) {
        List<User> users = baseMapper.selectList(new QueryWrapper<User>()
                .lambda()
                .eq(User::getUserName, user.getUserName())
                .eq(User::getPassword, user.getPassword())
        );
        if(users.size() == 0){
            throw new BussException(ResultCodeEnum.PASSWORD_INVALID);
        }
        return  Result.success(users.get(0));
    }

    @Override
    public Result<List<User>> getAll() {
        List<User> all = userDao.getAll();
        Result<List<User>> success = Result.success(all);
        success.successOrThrow();
        return success;
    }

    @Override
    public Result update(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getId,user.getId());

        userDao.selectList(queryWrapper);


        userDao.updateByPrimaryKeySelective(user);
        return Result.success();
    }

    /**
     *导出所有用户信息
     * @param response
     */
    @Override
    public void witeExcel(HttpServletResponse response) {
        //获取用户信息
        List users = userDao.selectList(null).stream().map(user -> {
            User.UserDto userDto = new User.UserDto();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }).collect(Collectors.toList());
        ExcelUtil.writeExcel(response,users,"用户信息表","用户信息",new User.UserDto());
    }
}
