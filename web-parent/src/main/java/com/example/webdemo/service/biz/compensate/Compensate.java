package com.example.webdemo.service.biz.compensate;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author admin
 * @since 2.1.0 2020/6/2 14:19
 */
@Data
public class Compensate implements Serializable {
    private static final long serialVersionUID = 1377585630136697916L;
    /** EnumCompensateType */
    private String type;
    private String serialNo;
    private String param;
    private Date businessDate;

}
