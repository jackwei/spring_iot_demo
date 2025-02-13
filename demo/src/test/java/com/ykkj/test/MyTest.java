package com.ykkj.test;

import cn.hutool.core.date.DateUtil;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.Date;

public class MyTest {

    public static void main(String[] args) throws UnsupportedEncodingException, URISyntaxException, MalformedURLException {



        // test git
        //

        //String resourcePath = MyTest.class.getClassLoader().getResource("").getPath();
        URI uri = MyTest.class.getClassLoader().getResource("").toURI();
        String resourcePath = Paths.get(uri).toString();

        System.out.println(resourcePath);
        String libpath2 = URLDecoder.decode(resourcePath, "UTF-8");
        System.out.println(libpath2);
        System.out.println(System.getProperty("user.dir")+"/demo/src/main/resources");


        System.out.println(DateUtil.now());
        System.out.println(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));

    }
}
