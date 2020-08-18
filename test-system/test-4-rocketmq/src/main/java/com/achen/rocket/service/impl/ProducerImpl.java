package com.achen.rocket.service.impl;

import com.achen.common.RocketMQMsg;
import com.achen.rocket.entity.OrderEntity;
import com.achen.rocket.mapper.OrderMapper;
import com.achen.rocket.service.Producer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ProducerImpl implements Producer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RocketMQMsg rocketMQMsg;

    @Autowired
    OrderMapper orderMapper;

    @Override
    @Transactional
    public String sendOrder() {
        // 提前生成我们的订单id
        String orderId = null;
            orderId = System.currentTimeMillis() + "";
            OrderEntity orderEntity = createOrder(orderId);
            Message message = rocketMQMsg.getRocketMsg(orderEntity);
            rocketMQTemplate.sendMessageInTransaction("transactionalProducer",
                    "orderTopic",message,null);
        return orderId;
    }

    public OrderEntity createOrder(String orderId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("测试订单");
        orderEntity.setOrderCreatetime(new Date());
        // 价格是300元
        orderEntity.setOrderMoney(300d);
        // 状态为 未支付
        orderEntity.setOrderState(0);
        Long commodityId = 30L;
        // 商品id
        orderEntity.setCommodityId(commodityId);
        orderEntity.setOrderId(orderId);
        return orderEntity;
    }
}
