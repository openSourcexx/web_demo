package com.example.webdemo.vo.response;

import java.io.Serializable;

/**
 * 批量导入响应
 *
 * @author safe
 * @date 2021/2/19
 */
public class BatchImportResp implements Serializable {
    private static final long serialVersionUID = -9186354590561703339L;
    /***
     * 导入总数
     */
    private int totalImportNum;

    /***
     * 导入成功总数
     */
    private int successImportNum;

    /***
     * 导入失败总数
     */
    private int failImportNum;
}
