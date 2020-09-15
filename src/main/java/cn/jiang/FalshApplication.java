package cn.jiang;

import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * JiangZhou
 * 2020/8/23
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("cn.jiang.dao")
@EnableNacosDiscovery
public class FalshApplication {
    public static void main(String[] args) {
        SpringApplication.run(FalshApplication.class,args);
    }
}
