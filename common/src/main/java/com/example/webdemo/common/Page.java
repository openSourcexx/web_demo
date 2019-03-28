package com.example.webdemo.common;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @author tangaq
 * @date 2019/3/21
 */
public class Page {
    private String pageNo;
    private String pageSize;

    public int getPageNo() {
        int intPageNo = 1;
        if (!StringUtils.isEmpty(pageNo)) {
            try {
                intPageNo = Integer.parseInt(pageNo);
            } catch (NumberFormatException e) {
                intPageNo = 1;
            }
        }
        return intPageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        int intPageSize = 10;
        if (!StringUtils.isEmpty(pageSize)) {
            try {
                intPageSize = Integer.parseInt(pageSize);
            } catch (NumberFormatException e) {
                intPageSize = 10;
            }
        }
        return intPageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置分页初始值
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static int getPageStart(int pageNo,int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 分页帮助类，设置example对象pageNo、pageSize
     * @param o example
     * @param pageNo
     * @param pageSize
     */
    public static void  pageHelp(Object o, int pageNo, int pageSize) {
        try {
            Class<?> clazz = o.getClass();
            Field f1 = clazz.getDeclaredField("pageNo");
            f1.setAccessible(true);
            f1.set(o,getPageStart(pageNo,pageSize));

            Field f2 = clazz.getDeclaredField("pageSize");
            f2.setAccessible(true);
            f2.set(o,pageSize);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
