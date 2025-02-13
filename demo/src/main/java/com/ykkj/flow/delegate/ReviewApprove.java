package com.ykkj.flow.delegate;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName ReviewApprove.java
 * @Description TODO
 * @createTime 2021年12月09日
 */
@Slf4j
public class ReviewApprove implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        //可以发送消息给某人
        log.info("通过，userId是：{}",delegateExecution.getVariable("userId"));
    }
}
