package cn.jiang.service.impl;

import cn.jiang.exception.BussException;
import cn.jiang.result.Result;
import cn.jiang.service.RedisService;
import cn.jiang.util.RedisUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * jiangzhou
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {
    @Resource
    RedisUtil redisUtil;
    @Resource
    RedissonClient redissonClient;
    @Override
    public Result reduceValue(String lockKey,String key, String value) {
//        System.out.println("reduceValue start");
        //根据key获取锁
        RLock lock = redissonClient.getLock(lockKey);
        boolean getLock = false;
        String andSet = "";
        try {
            if (getLock = lock.tryLock(0,5, TimeUnit.SECONDS)){
                //执行业务逻辑
                System.out.println("拿到锁干活");
                String s = redisUtil.get(key);
                int i = Integer.parseInt(s);
                int j = Integer.parseInt(value);
                int num = i - j;
                andSet = redisUtil.getAndSet(key, num + "");
//                Thread.sleep(6000);
                return  Result.success(andSet);
            }else {
                System.out.println("Redisson分布式锁没有获得锁:"+key+",ThredName="+Thread.currentThread().getName());
                return  Result.success("Redisson分布式锁没有获得锁:"+key);
            }

        } catch (InterruptedException e) {
//            log.error("Redisson 获取分布式锁异常,异常信息:{}",e);
            throw new BussException("Redisson 获取分布式锁异常");
        }
        //        finally {
//            if (!getLock){
//                return Result.success(andSet);
//            }
        //如果演示的话需要注释该代码;实际应该放开
        //lock.unlock();
        //log.info("Redisson分布式锁释放锁:{},ThreadName :{}", KeyConst.REDIS_LOCK_KEY, Thread.currentThread().getName());
//        }
    }
}
