package cn.jiang.configuration;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

//@Configuration
//@EnableScheduling
//@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ScheduledLockConfig {
//    @Resource
//    RedisConnectionFactory redisConnectionFactory;
//
//    @Bean
//    @Qualifier("lockProvider")
//    public LockProvider lockProvider() {
//        LockProvider lockProvider = new RedisLockProvider(redisConnectionFactory);
//        return lockProvider;
//    }
}
