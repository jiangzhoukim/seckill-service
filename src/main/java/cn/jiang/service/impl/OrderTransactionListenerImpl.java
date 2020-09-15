package cn.jiang.service.impl;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unused")
public class OrderTransactionListenerImpl implements TransactionListener {
    private ConcurrentHashMap<String, Integer> countHashMap = new ConcurrentHashMap<>();

    private final static int MAX_COUNT = 5;


    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        //
        String bizUniNo = msg.getUserProperty("bizUniNo"); // 从消息中获取业务唯一ID。
        // 将bizUniNo入库，表名：t_message_transaction,表结构  bizUniNo(主键),业务类型。
        return LocalTransactionState.UNKNOW;
    }

    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        Integer status = 0;
        // 从数据库查查询t_message_transaction表，如果该表中存在记录，则提交，
        String bizUniNo = msg.getUserProperty("bizUniNo"); // 从消息中获取业务唯一ID。
        // 然后t_message_transaction 表，是否存在bizUniNo，如果存在，则返回COMMIT_MESSAGE，
        // 不存在，则记录查询次数，未超过次数，返回UNKNOW，超过次数，返回ROLLBACK_MESSAGE

        if(query(bizUniNo) > 0 ) {
            return LocalTransactionState.COMMIT_MESSAGE;
        }

        return rollBackOrUnown(bizUniNo);
    }

    public int query(String bizUniNo) {
        return 1; //select count(1) from t_message_transaction a where a.biz_uni_no=#{bizUniNo}
    }

    public LocalTransactionState rollBackOrUnown(String bizUniNo) {
        Integer num = countHashMap.get(bizUniNo);

        if(num != null &&  ++num > MAX_COUNT) {
            countHashMap.remove(bizUniNo);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        if(num == null) {
            num = new Integer(1);
        }

        countHashMap.put(bizUniNo, num);
        return LocalTransactionState.UNKNOW;

    }
}
