package cn.jiang.controller;

import cn.jiang.result.Result;
import cn.jiang.service.RedisService;
import cn.jiang.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * jiangzhou 2020/8/23
 */
@RestController
@RequestMapping("/lock")
@Slf4j
@EnableAutoConfiguration
public class LockController {

    @Resource
    RedissonClient redissonClient;

    @Resource
    RedisUtil redisUtil;

    @Resource
    RedisService redisService;



    @GetMapping("/setKey")
    public Result setRedis(@RequestParam("key") String key, @RequestParam("value") String value){
        redisUtil.set(key,value);
        return  Result.success();
    }

    @GetMapping("/getKey")
    public Result<String> getRedis(@RequestParam("key") String key){
        String keys = redisUtil.get(key);
        return  Result.success(keys);
    }

    @GetMapping("/reduceValue")
    public Result<String> reduceValue(@RequestParam("lockKey") String lockKey,@RequestParam("key") String key, @RequestParam("value")String value){
        log.info("ThreadName:{} reduceValue start",Thread.currentThread().getName());
        Result result = redisService.reduceValue(lockKey,key, value);
        return  result;
    }

    @GetMapping("/task")
    public void task(){
        log.info("task start");
        //为划分颗粒度，我们项目将以商品id作为锁的key
        RLock lock = redissonClient.getLock("testLock");
        boolean getLock = false;

        try {
            if (getLock = lock.tryLock(0,5, TimeUnit.SECONDS)){
                //执行业务逻辑
                System.out.println("拿到锁干活");
                Thread.sleep(1000);

            }else {
                log.info("Redisson分布式锁没有获得锁:{},ThreadName:{}","testLock",Thread.currentThread().getName());
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

}
