package com.example.webdemo.common;

import org.springframework.util.StringUtils;

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

    public static int getPageStart(int pageNo,int pageSize) {
        return (pageNo - 1) * pageSize;
    }
}
