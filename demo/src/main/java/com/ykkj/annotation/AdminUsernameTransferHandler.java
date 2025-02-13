package com.ykkj.annotation;

import com.ykkj.enums.TransferStrategyEnum;
import org.springframework.stereotype.Component;

@Component
public class AdminUsernameTransferHandler implements TransferHandler {

    @Override
    public Object getData(Object object) {
        //执行转换逻辑......
        return "123123";
    }

    @Override
    public TransferStrategyEnum getTransferStrategyEnum() {
        return TransferStrategyEnum.ADMIN_USERNAME;
    }


}
