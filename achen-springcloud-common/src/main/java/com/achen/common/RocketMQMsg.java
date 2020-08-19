package com.achen.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class RocketMQMsg {

    public Message getRocketMsg(Object o){
        String msg = JSONObject.toJSONString(o);
        MessageBuilder<String> stringMessageBuilder = MessageBuilder.withPayload(msg);
        stringMessageBuilder.setHeader("msg", msg);
        Message message = stringMessageBuilder.build();
        return message;
    }

}
