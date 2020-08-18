package com.achen.utils;

import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

@Component
public class RocketMQTransaction {

    @Autowired
    private TransactionUtils transactionUtils;

    public void Rollback(TransactionStatus t){
        transactionUtils.rollback(t);
        RocketMQLocalTransactionState rollback = RocketMQLocalTransactionState.ROLLBACK;
    }

}
