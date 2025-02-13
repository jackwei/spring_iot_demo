package com.ykkj.annotation;

import com.ykkj.enums.TransferStrategyEnum;

import javax.annotation.PostConstruct;

public interface TransferHandler {

    @PostConstruct
    default void init() {
        /**
         * 注册策略对象
         */
        TransferContext.register(this);
    }

    /**
     * 根据转换字段对象获取转换值
     *
     * @param object 转换字段对象
     * @return 转换对象值
     */
    Object getData(Object object);

    /**
     * 当前Handler的转换类型
     *
     * @return
     */
    TransferStrategyEnum getTransferStrategyEnum();
}

