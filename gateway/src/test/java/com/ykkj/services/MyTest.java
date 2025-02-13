package com.ykkj.services;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;

import java.util.Date;

public class MyTest {


    public static void main(String[] args) {
        System.out.println(DateUtil.now());
        System.out.println(DateUtil.format(new Date(),"yyyy-MM-dd"));
    }
}
