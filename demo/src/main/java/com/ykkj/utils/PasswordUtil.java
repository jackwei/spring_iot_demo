package com.ykkj.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lw
 * @version 1.0.0
 * @ClassName PasswordUtil.java
 * @Description TODO
 * @createTime 2023年03月22日
 */
public class PasswordUtil {


    public static void main(String[] args) {
        System.out.println(22);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("Ykkj#0335"));

        //"Ykkj#0335"
        //$2a$10$FZ8qkoSTi.4oLUrc9cYtDu.f87nFV/Ab19078xow7wp4Wco4pfQA2
    }

}
