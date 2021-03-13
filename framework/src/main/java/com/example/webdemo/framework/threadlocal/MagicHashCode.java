package com.example.webdemo.framework.threadlocal;

/**
 * 计算threadlocal扩容后下标索引
 * 参考:https://www.cnblogs.com/dennyzhangdd/p/7978455.html
 *
 * @author safe
 * @date 2021/3/6
 */
public class MagicHashCode {
    //ThreadLocal中定义的hash魔数
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        hashCode(16);
        hashCode(32);
        hashCode(64);
        hashCode(128);
    }

    /**
     * 此算法在长度为2的N次方的数组上，确实可以完美散列
     *
     * @param len
     */
    public static void hashCode(Integer len) {
        int hashCode = 0;
        for (int i = 0; i < len; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;// 每次递增HASH_INCREMENT
            System.out.print(hashCode & (len - 1)); // index
            System.out.print(" ");
        }
        System.out.println();
    }
}
