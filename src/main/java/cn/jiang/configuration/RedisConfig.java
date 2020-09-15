package cn.jiang.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
/**
 * jiangzhou
 * 2020/8/23
 */
@Configuration
public class RedisConfig {
    @Resource
    RedisConnectionFactory redisConnectionFactory;

    /**
     * Description: aiming to create a template for Long and user(To be a user cache)
     *
     * @return redisTemplate<Long, User>
     */
    @Bean
    @Qualifier("redisDistributionLockTemplate")
    public RedisTemplate<String, Object> redisDistributionLockTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
