package com.example.webdemo.sofaboot.trancer;

/**
 * todo
 *
 * @author 唐安全
 * @date 2020/09/24
 */
public class TranceUtil {
    /**
     * 16进制转10进制
     *
     * @param: [hex]
     * @return: int
     * @description: 按位计算，位值乘权重
     */
    public static int hexToDecimal(String hex) {
        int outcome = 0;
        for (int i = 0; i < hex.length(); i++) {
            char hexChar = hex.charAt(i);
            outcome = outcome * 16 + charToDecimal(hexChar);
        }
        return outcome;
    }

    /**
     * @param: [c]
     * @return: int
     * @description:将字符转化为数字
     */
    public static int charToDecimal(char c) {
        if (c >= 'A' && c <= 'F')
            return 10 + c - 'A';
        else
            return c - '0';
    }

    //测试程序
    public static void main(String... args) {
        // 10.209.52.143
        String content = "0ad1348f";
        content = "0e280150";

        getTranceIp(content);
        //将全部的小写转化为大写
        System.out.println(Integer.toHexString(Integer.parseInt("14")));
        System.out.println(Integer.toHexString(Integer.parseInt("40")));
        System.out.println(Integer.toHexString(Integer.parseInt("4")));
        System.out.println(Integer.toHexString(Integer.parseInt("7")));

    }

    /**
     * 01,23,45,67
     *
     * @param content
     */
    private static void getTranceIp(String content) {
        StringBuilder builder = new StringBuilder();
        content = content.toUpperCase();
        char[] arrs = content.toCharArray();
        for (int i = 0; i < content.length(); i += 2) {
            builder.append(hexToDecimal(new String(arrs, i, 2)));
            if (i < content.length() - 2) {
                builder.append(".");
            }
        }
        System.out.println(builder.toString());
    }
}
