package com.example.webdemo.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 批量导入请求
 *
 * @author safe
 * @date 2021/2/19
 */
@Data
public class BatchImportRequest implements Serializable {
    private static final long serialVersionUID = -2283933617865603122L;
    /***
     * 导入文件路径
     */
    @NotBlank(message = "导入文件路径")
    private String filePath;

    /***
     * 导入流水
     */
    @NotBlank(message = "导入流水")
    private String bizSerialNo;
}
