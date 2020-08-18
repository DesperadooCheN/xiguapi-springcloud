package com.achen.rocket.consumer;

import com.achen.rocket.entity.TestEntity;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

//没啥用
//@Service
//@RocketMQMessageListener(topic = "test-insert-topic1", consumerGroup = "test-insert-topic1")
//public class TestConsumer implements RocketMQListener<TestEntity> {
//    @Override
//    public void onMessage(TestEntity entity) {
//        System.out.println("t"+entity.toString());
//    }
//}
