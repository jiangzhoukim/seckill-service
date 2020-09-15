package cn.jiang.controller;

import cn.jiang.ciphertext.Fence;
import cn.jiang.mode.User;
import cn.jiang.result.Result;
import cn.jiang.service.UserService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/producer")
@Slf4j
@EnableAutoConfiguration
public class ProducerController {

    @Resource
    private UserService userService;

    @Resource
    private DefaultMQProducer defaultProducer;

    @Resource
    private TransactionMQProducer transactionProducer;

    /**
     * 发送普通消息
     */
    @GetMapping("/sendMessage")
    public void sendMsg() {

        for(int i=0;i<100;i++){
            User user = new User();
            user.setId(Long.parseLong(String.valueOf(i)));
            user.setUserName("jhp"+i);
            String json = JSON.toJSONString(user);
            Message msg = new Message("user-topic","white",json.getBytes());
            try {
                SendResult result = defaultProducer.send(msg);
                System.out.println("消息id:"+result.getMsgId()+":"+","+"发送状态:"+result.getSendStatus()+",userName"+user.getUserName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送普通消息
     */
    @GetMapping("/sendMessageTest")
    public void sendMessageTest() {
        for(int i=0;i<100;i++){
            User user = new User();
            user.setId(Long.parseLong(String.valueOf(i)));
            user.setUserName("jhp"+i);
            String json = JSON.toJSONString(user);
            Message msg = new Message("user-topic","purple",json.getBytes());
            try {
                SendResult result = defaultProducer.send(msg);
                System.out.println("消息id:"+result.getMsgId()+":"+","+"发送状态:"+result.getSendStatus()+",userName"+user.getUserName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送事务消息
     * @return
     */
    @GetMapping("/sendTransactionMess")
    public String sendTransactionMsg() {
        SendResult sendResult = null;


        try {
            // a,b,c三个值对应三个不同的状态
            String ms = "c";
            Message msg = new Message("user-topic","white",ms.getBytes());
            // 发送事务消息
            sendResult = transactionProducer.sendMessageInTransaction(msg,
//            (Message msg1, Object arg) -> {
//                String value = "";
//                if (arg instanceof String) {
//                    value = (String) arg;
//                }
//                if (value == "") {
//                    throw new RuntimeException("发送消息不能为空...");
//                } else if (value =="a") {
//                    return LocalTransactionState.ROLLBACK_MESSAGE;
//                } else if (value =="b") {
//                    return LocalTransactionState.COMMIT_MESSAGE;
//                }
//                return LocalTransactionState.ROLLBACK_MESSAGE;
//            },
                    4);
            System.out.println(sendResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult.toString();
    }

    public static void getMsg(){
        BiFunction<Message, Object, LocalTransactionState> m = (Message msg1, Object arg) -> {
            String value = "";
            if (arg instanceof String) {
                value = (String) arg;
            }
            if (value == "") {
                throw new RuntimeException("发送消息不能为空...");
            } else if (value == "a") {
                return LocalTransactionState.ROLLBACK_MESSAGE;
            } else if (value == "b") {
                return LocalTransactionState.COMMIT_MESSAGE;
            }
            return LocalTransactionState.ROLLBACK_MESSAGE;
        };
    }
    /**
     * 支持顺序发送消息
     */
    @GetMapping("/sendMessOrder")
    public void sendMsgOrder() {
        for(int i=0;i<100;i++) {
            User user = new User();
            user.setId(Long.parseLong(String.valueOf(i)));
            user.setUserName("jhp" + i);
            String json = JSON.toJSONString(user);
            Message msg = new Message("user-topic", "white", json.getBytes());
            try{
                SendResult result =  defaultProducer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        int index = ((Integer) arg) % mqs.size();
                        return mqs.get(index);
                    }
                },i);
                System.out.println("顺序消息id:"+result.getMsgId()+":"+","+"发送状态:"+result.getSendStatus()+",userName"+user.getUserName());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @GetMapping("/user")
    public Result getStr(@RequestParam("uid") Long uid){
        User user = userService.getUser(uid);
        Result<User> success = Result.success(user);
        User data = success.getData();
        return success;
    }

    @GetMapping("/addUser")
    public Result addUser(@RequestParam("userName") String name,@RequestParam("password") String password,@RequestParam("age") Integer age){
        User user = new User();
        user.setUserName(name);
        user.setAge(age);
        //密码加密
        user.setPassword(Fence.encryption(password));
        userService.addUser(user);
        return  Result.success();
    }

    @GetMapping("/login")
    public Result<User> signIn(@RequestParam("userName") String userName,@RequestParam("password") String password){
        User user = new User();
        user.setUserName(userName);
        password = Fence.encryption(password);
        user.setPassword(password);
        Result<User> login = userService.login(user);
        login.getDataWithThrow();
        return  login;
    }

}
