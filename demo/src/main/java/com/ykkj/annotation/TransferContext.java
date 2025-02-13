package com.ykkj.annotation;

import com.ykkj.enums.TransferStrategyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransferContext {

    private static final Map<TransferStrategyEnum, TransferHandler> TRANSFER_HANDLER_MAP = new ConcurrentHashMap<>();

    public static void register(TransferHandler transferHandler) {
        TRANSFER_HANDLER_MAP.put(transferHandler.getTransferStrategyEnum(), transferHandler);
    }

    /**
     * 数据转换逻辑
     */
    public static Object transfer(TransferStrategyEnum transferStrategyEnum, Object object) {
        if (object == null || CollectionUtils.isEmpty(TRANSFER_HANDLER_MAP)) {
            return null;
        }

        TransferHandler transferHandler = TRANSFER_HANDLER_MAP.get(transferStrategyEnum);

        if (transferHandler == null) {
            return null;
        }
        return transferHandler.getData(object);
    }
}

