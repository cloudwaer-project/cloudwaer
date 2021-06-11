package com.cloudwaer.common.utils;

import java.util.Random;

public class StringRandom {
    public static String getString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);// 取一个随机的ASCII码，大写字母
                    sb.append(String.valueOf((char) result));// 转字符存起来
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);// 取一个随机的ASCII码，小写字母
                    sb.append(String.valueOf((char) result));// 转字符存起来
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));// 数字
                    break;
            }
        }
        String s = sb.toString().toUpperCase();
        return s;
    }

}
