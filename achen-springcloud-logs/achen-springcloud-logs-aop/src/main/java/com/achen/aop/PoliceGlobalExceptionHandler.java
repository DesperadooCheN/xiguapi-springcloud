package com.achen.aop;

import com.achen.base.BaseApiService;
import com.achen.base.BaseResponse;
import com.achen.common.RocketMQMsg;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.messaging.Message;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;

@ControllerAdvice
public class PoliceGlobalExceptionHandler extends BaseApiService {

    @Value("${spring.application.name}")
    private String serverIdName;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RocketMQMsg rocketMQMsg;

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResponse<String> errorResult(ServerWebExchange exchange, Exception ex) throws UnknownHostException {
        //采用全局捕获异常
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        ServerRequest serverRequest = new DefaultServerRequest(exchange);
        StackTraceElement[] stackTrace = ex.getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[0];
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(stackTraceElement));
        jsonObject.put("parameterContent",queryParams);
        jsonObject.put("errorContent",ex.getMessage());
        jsonObject.put("serviceId",serverIdName);
        jsonObject.put("createTime",new Date());
        jsonObject.put("messageId", UUID.randomUUID());
        jsonObject.put("serverIp",getServerAddress());
        send(jsonObject);
        return setResultError("系统错误");
    }

    private String getServerAddress() throws UnknownHostException {
        InetAddress ip4 = Inet4Address.getLocalHost();
        return ip4.getHostAddress()+":"+serverPort;
    }

    private void send(JSONObject jsonObject){
        Message rocketMsg = rocketMQMsg.getRocketMsg(jsonObject);
        rocketMQTemplate.send("error-log",rocketMsg);
    }
}
