package com.achen.log.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorLogEntity {
    private String messageId;
    private String serviceId;
    private String className;
    private String methodName;
    private String parameterContent;
    private String errorContent;
    private Date createTime;
    private int lineNumber;
    private String serverIp;

    public ErrorLogEntity(String messageId, String serviceId, String className, String methodName, String parameterContent, String errorContent, Date createTime, int lineNumber, String serverIp) {
        this.messageId = messageId;
        this.serviceId = serviceId;
        this.className = className;
        this.methodName = methodName;
        this.parameterContent = parameterContent;
        this.errorContent = errorContent;
        this.createTime = createTime;
        this.lineNumber = lineNumber;
        this.serverIp = serverIp;
    }
}
