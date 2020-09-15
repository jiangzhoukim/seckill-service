package cn.jiang.common.rocketmq.producer;

import cn.jiang.mode.User;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.io.Serializable;

public class Producer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        producer.setNamesrvAddr("localhost:9876");
        producer.setInstanceName("rmq-instance");
        producer.start();
        try {
            for (int i=0;i<100;i++){
                User user = new User();
                user.setLoginName("abc"+i);
                user.setPwd(String.valueOf(i));
                Message message = new Message("log-topic", "user-tag",JSON.toJSONString(user).getBytes());
                System.out.println("生产者发送消息:"+JSON.toJSONString(user));
                producer.send(message);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }

    /**
     * 发送用户消息
     */
    @Data
    static  class User implements Serializable {
        private String loginName;
        private String pwd;
    }
    }
