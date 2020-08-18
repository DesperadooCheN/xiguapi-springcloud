package com.achen.log.consumer;

import com.achen.log.entity.ErrorLogEntity;
import com.achen.log.mapper.ErrorLogMapper;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "error-log",consumerGroup = "error-log")
public class LogConsumer implements RocketMQListener<String> {


    @Autowired
    ErrorLogMapper errorLogMapper;

    @Override
    public void onMessage(String message) {
        if (message==null){
            return;
        }
        ErrorLogEntity errorLogEntity = JSON.parseObject(message,ErrorLogEntity.class);
        int result= errorLogMapper.insertErrorLog(errorLogEntity);
        try {
            if (result<=0)
                throw new Exception("触发重复消费机制");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
