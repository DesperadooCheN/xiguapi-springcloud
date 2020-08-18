package com.achen.rocket.controller;

import com.achen.rocket.entity.TestEntity;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @RequestMapping("testSendRocket")
    public String testSendRocket() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        long orderId = System.currentTimeMillis();
        String insertSql = getSqlMsg("insert", orderId);
        TestEntity testEntity = new TestEntity("111","name1");
//        Message insertMsg = new Message("test-insert-topic1",insertSql.getBytes());
        Message updateMsg = new Message();
        Message deleteMSg = new Message();
        rocketMQTemplate.convertAndSend("test-insert-topic1",testEntity);
//        rocketMQTemplate.getProducer().send(insertMsg
//                , new MessageQueueSelector() {
//                    @Override
//                    public MessageQueue select(List<MessageQueue> mqs, Message msg,
//                                               Object arg) {
//                        // 该消息存放到队列0中
//                        return mqs.get(0);
//                    }
//                }, orderId);

//        rocketMQTemplate.getProducer().send(updateMsg, new MessageQueueSelector() {
//            @Override
//            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                return mqs.get(0);
//            }
//        }, orderId);
//
//        rocketMQTemplate.getProducer().send(deleteMSg, new MessageQueueSelector() {
//            @Override
//            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
//                return mqs.get(0);
//            }
//        },orderId);
        return "1";
    }

    public String getSqlMsg(String type, Long orderId) {
        JSONObject dataObject = new JSONObject();
        dataObject.put("type", type);
        dataObject.put("orderId", orderId);
        return dataObject.toJSONString();
    }
}
