package com.ykkj.service;

import com.ykkj.annotation.ServiceSwitch;
import com.ykkj.constant.Constants;
import com.ykkj.utils.Result;
import com.ykkj.utils.ResultUtil;
import org.springframework.stereotype.Service;

@Service
public class RegService {

    @ServiceSwitch(switchKey = Constants.ConfigCode.REG_PAY_SWITCH)
    public Result createOrder() {

        return ResultUtil.success("挂号下单成功");


    }
}
