package cn.jiang.common.rocketmq.producer;

import cn.jiang.common.rocketmq.content.UserContent;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Component
public class UserProducer {


    /**
     * 生产者的组名
     */
    @Value("${suning.rocketmq.producerGroup}")
    private String producerGroup;

    /**
     * NameServer 地址
     */
    @Value("${suning.rocketmq.namesrvaddr}")
    private String namesrvAddr;

//    @PostConstruct
    public void produder() {
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
            for (int i = 0; i < 50; i++) {
                UserContent userContent = new UserContent();
                userContent.setPassword(String.valueOf(i));
                userContent.setUserName("abc"+i);
                String jsonstr = JSON.toJSONString(userContent);
                System.out.println("发送消息:"+jsonstr);
                Message message = new Message("user-topic", "user-tag", jsonstr.getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult result = producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                        Integer index = (Integer)o;
                        return list.get(index);
                    }
                },1);
                System.err.println("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }


//    public void test(){
//        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
//        producer.setNamesrvAddr(namesrvAddr);
//        try {
//            //消息会发送到队列1中，send的第三个变量arg表示发到下标为几的队列
//            Message message = new Message("TopicTest" /* Topic */,
//                    "顺序" /* Tag */,"顺序信息".getBytes(RemotingHelper.DEFAULT_CHARSET));
//            producer.send(message, new MessageQueueSelector() {
//                @Override
//                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
//                    Integer index = (Integer)o;
//                    return list.get(index);
//                }
//            },1);
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            producer.shutdown();
//        }
//
//
//
//    }


}
