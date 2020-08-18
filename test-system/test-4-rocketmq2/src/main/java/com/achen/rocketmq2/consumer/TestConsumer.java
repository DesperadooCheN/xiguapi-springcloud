package com.achen.rocketmq2.consumer;

import com.achen.rocketmq2.entity.TestEntity;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "test-insert-topic1", consumerGroup = "test-insert-topic1")
public class TestConsumer implements RocketMQListener<TestEntity> {
    @Override
    public void onMessage(TestEntity entity) {
        System.out.println("t"+entity.toString());
    }
}
