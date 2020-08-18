package com.achen.rocketmq2.consumer;

import com.achen.rocketmq2.entity.DispatchEntity;
import com.achen.rocketmq2.entity.OrderEntity;
import com.achen.rocketmq2.mapper.DispatchMapper;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "orderTopic",consumerGroup = "transactionalProducer")
public class TransactionalConsumer implements RocketMQListener<String> {

    @Autowired
    DispatchMapper dispatchMapper;

    @Override
    public void onMessage(String message) {
        OrderEntity orderEntity = JSONObject.parseObject(message, OrderEntity.class);
        String orderId = orderEntity.getOrderId();
        // 模拟userid为=123456
        DispatchEntity dispatchEntity = new DispatchEntity(orderId, 123456L);
        dispatchMapper.insertDistribute(dispatchEntity);
        System.out.println("执行完毕 orderId :" + orderId);
    }
}
