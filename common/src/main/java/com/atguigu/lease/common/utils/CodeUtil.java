package com.atguigu.lease.common.utils;

import org.junit.jupiter.api.ClassOrderer;

import java.util.Random;

/**
 * ClassName: CodeUtil
 * Package: com.atguigu.lease.common.utils
 * Description:
 *
 * @Author HL
 * @Create 11/7/2024 2:31 PM
 * @Version:
 */
public class CodeUtil {
    public static String getRandomCode(Integer length){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < length; i++){
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
