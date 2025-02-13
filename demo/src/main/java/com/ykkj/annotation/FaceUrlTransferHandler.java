package com.ykkj.annotation;

import com.ykkj.enums.TransferStrategyEnum;
import org.springframework.stereotype.Component;

@Component
public class FaceUrlTransferHandler implements TransferHandler {

    @Override
    public Object getData(Object object) {
        //执行转换逻辑......
        System.out.println(object);
        return "faceUrlTransfer";
    }

    @Override
    public TransferStrategyEnum getTransferStrategyEnum() {
        return TransferStrategyEnum.FACE_URL;
    }


}
