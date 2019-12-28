package com.example.webdemo.vo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InitSerialReqDto implements Serializable {
    private static final long serialVersionUID = 6567594028027364659L;
    /** 业务流水编码 */
    private String serialCode;
    /** 执行次数 */
    private int times;
    /** 租户号 */
    @NotNull(message = "租户号不能为空")
    private String tenantId;
}
