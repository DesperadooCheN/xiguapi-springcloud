package com.achen.rocket.listener;

import com.achen.rocket.entity.OrderEntity;
import com.achen.rocket.mapper.OrderMapper;
import com.achen.utils.RocketMQTransaction;
import com.achen.utils.TransactionUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

@Slf4j
@Component
@RocketMQTransactionListener(txProducerGroup = "transactionalProducer")
public class SyncProducerListener implements RocketMQLocalTransactionListener {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TransactionUtils transactionUtils;

    @Autowired
    private RocketMQTransaction rocketMQTransaction;
    /**
     * 执行我们订单的事务
     *
     * @param msg
     * @param arg
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        MessageHeaders headers = msg.getHeaders();
        Object object = headers.get("msg");
        if (object == null) {
            return null;
        }
        String orderMsg = (String) object;
        OrderEntity orderEntity = JSONObject.parseObject(orderMsg, OrderEntity.class);
        TransactionStatus t = null;
        try {
            t = transactionUtils.begin();
            int result = orderMapper.addOrder(orderEntity);
            transactionUtils.commit(t);
            if (result <= 0) {
                return RocketMQLocalTransactionState.ROLLBACK;
            }
//            // 告诉我们的Broke可以消费者该消息
//            return RocketMQLocalTransactionState.COMMIT;
            return null;
        } catch (Exception e) {
            if (t != null) {
                rocketMQTransaction.Rollback(t);
            }
        }
        //add.Order
        return null;
    }

    /**
     * 提供给我们的Broker定时检查
     *
     * @param msg
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        MessageHeaders headers = msg.getHeaders();
        Object object = headers.get("msg");
        if (object == null) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        String orderMsg = (String) object;
        OrderEntity orderEntity = JSONObject.parseObject(orderMsg, OrderEntity.class);
        String orderId = orderEntity.getOrderId();
        // 直接查询我们的数据库
        OrderEntity orderDbEntity = orderMapper.findOrderId(orderId);
        if (orderDbEntity == null) {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        return RocketMQLocalTransactionState.COMMIT;
    }
}
