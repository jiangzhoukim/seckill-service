package cn.jiang.scheduled;


import cn.jiang.mode.User;
import cn.jiang.result.Result;
import cn.jiang.service.UserService;
import cn.jiang.util.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * JiangZhou
 */
@Slf4j
@Component
public class TestScheduled {

    private static  final  int lockTime = 1000*11;

    @Resource
    RedisUtil redisUtil;

    @Resource
    RedissonClient redissonClient;

    @Resource
    UserService userService;

    /**
     * 定时任务测试方法
     */
//    @Scheduled(fixedDelay = 1000*5)
//    @Scheduled(fixedRate = 1000*30)
    @SchedulerLock(name = "testGetkeys" , lockAtMostFor = lockTime+"", lockAtLeastFor = lockTime+"")
    public void testScheduled(){
        log.info("testScheduled 定时任务启动。。。。");
        //为划分颗粒度，我们项目将以方法名作为分布式锁的key
        RLock lock = redissonClient.getLock("testScheduled");
        boolean getLock = false;

        try {
            if (getLock = lock.tryLock(0,5, TimeUnit.SECONDS)){
                //执行业务逻辑
                System.out.println("拿到锁干活");
                //将user表中的数据拿到redis中
                Result<List<User>> all = userService.getAll();
                List<User> userList = all.getData();
                if(userList.size() != 0){
                    for (User user:userList){
                        Long id = user.getId();
                        String userJson = JSON.toJSON(user).toString();
//                        redisUtil.set(id+"",userJson);
                        redisUtil.setEx(id+"",userJson,20,TimeUnit.SECONDS);
                    }
                }
            }else {
                log.info("Redisson分布式锁没有获得锁:{},ThreadName:{}","testScheduled",Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            log.error("Redisson 获取分布式锁异常,异常信息:{}",e);
        }finally {


            if (!getLock){
                return;
            }
            //如果演示的话需要注释该代码;实际应该放开
            //lock.unlock();
            //log.info("Redisson分布式锁释放锁:{},ThreadName :{}", KeyConst.REDIS_LOCK_KEY, Thread.currentThread().getName());
        }
    }


    /**
     * 获取
     */
//    @Scheduled(fixedRate = 1000*10)
    public void testScheduled2(){
        log.info("testScheduled2 获取user定时任务启动。。。。");
        //为划分颗粒度，我们项目将以商品id作为锁的key
        RLock lock = redissonClient.getLock("testScheduled2");
        boolean getLock = false;

        try {
            if (getLock = lock.tryLock(0,5, TimeUnit.SECONDS)){
                //执行业务逻辑
                    //获得所有key
                List<String> allKeys = redisUtil.getAllKeys();
                allKeys.forEach(p-> System.out.println("key:"+p));
                System.out.println("=======================================");
                //获得所有user
                List<User> data = userService.getAll().getData();
                if(data.size() == 0){
                    for (String key:allKeys){
                        if(!key.equals("auth:jwt:user:black-list")){
                            String value = redisUtil.get(key);
                            System.out.println("key:"+key+",value:"+value);
                        }
                    }
                }else{
                    for (int i =0;i< allKeys.size();i++){
                        for (int j=0;j<data.size();j++){
                            String key = allKeys.get(i);
                            String id = (data.get(j).getId()) + "";
                            if(!key.equals(id)){
                                String value = redisUtil.get(key);
                                System.out.println("key:"+key+",value:"+value);
                            }
                        }
                    }
                }
            }else {
                log.info("Redisson分布式锁没有获得锁:{},ThreadName:{}","testScheduled2",Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            log.error("Redisson 获取分布式锁异常,异常信息:{}",e);
        }finally {


            if (!getLock){
                return;
            }
            //如果演示的话需要注释该代码;实际应该放开
            //lock.unlock();
            //log.info("Redisson分布式锁释放锁:{},ThreadName :{}", KeyConst.REDIS_LOCK_KEY, Thread.currentThread().getName());
        }
    }

//    @Scheduled(fixedRate = 1000*3)
    public void getKey(){
        log.info("getKey 获取user定时任务启动。。。。");
        //为划分颗粒度，我们项目将以商品id作为锁的key
        RLock lock = redissonClient.getLock("getKey");
        boolean getLock = false;

        try {
            if (getLock = lock.tryLock(0,5, TimeUnit.SECONDS)){
                //执行业务逻辑
                System.out.println("拿到锁干活");
                Result<List<User>> all = userService.getAll();
                List<User> data = all.getData();
                List<String> allKeys = redisUtil.getAllKeys();
                for (User user:data){
                    String key = user.getId()+"";
                    String value = redisUtil.get(key);
                    System.out.println("key:"+key+"=====value:"+value);
                    JSONObject jsonObject = JSON.parseObject(value);
                    User user1 = JSON.toJavaObject(jsonObject, User.class);
                    if(user1 ==null){
                        System.out.println("user的数据为:"+userService.getUser(user.getId()));
                    }else{
                        System.out.println("user1的数据为:"+user1);
                    }
                }
            }else {
                log.info("Redisson分布式锁没有获得锁:{},ThreadName:{}","getKey",Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            log.error("Redisson 获取分布式锁异常,异常信息:{}",e);
        }finally {


            if (!getLock){
                return;
            }
            //如果演示的话需要注释该代码;实际应该放开
            //lock.unlock();
            //log.info("Redisson分布式锁释放锁:{},ThreadName :{}", KeyConst.REDIS_LOCK_KEY, Thread.currentThread().getName());
        }
    }

    public void updateUser(){

    }
}
