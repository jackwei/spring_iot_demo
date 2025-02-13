package com.ykkj.flow.service;

import org.flowable.idm.api.UserQuery;
import org.flowable.idm.engine.IdmEngineConfiguration;
import org.flowable.idm.engine.impl.IdmIdentityServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName CustomIdmIdentityServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月14日
 */
public class CustomIdmIdentityServiceImpl extends IdmIdentityServiceImpl {

    IdmEngineConfiguration idmEngineConfiguration;

    public CustomIdmIdentityServiceImpl(IdmEngineConfiguration idmEngineConfiguration) {
        super(idmEngineConfiguration);
        //this.idmEngineConfiguration = idmEngineConfiguration;
    }

    @Override
    public UserQuery createUserQuery() {
        // 自定义的用户查询器实现
        return new CustomUserQueryImpl();
    }

}
