package com.example.webdemo.service.biz;

/**
 * todo
 *
 * @author admin
 * @since 2019/12/3 13:54
 */
public interface DemoBiz {
    public void testRpcConn();

    void compensate();

    /**
     * 多数据源
     */
    void otherDb();
}
