package com.example.webdemo.easypoi;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class ExcelDemo implements Serializable {

    private static final long serialVersionUID = -6696016871138207247L;
    @Excel(name = "合同编号")
    private String contractId;

    @Excel(name = "合同名")
    private String contractName;

    @Excel(name = "合同类型", replace = {"支用_loan", "授信_credit"})
    private String contractType;

    @Excel(name = "合同链接")
    private String contractUrl;

    @Excel(name = "状态")
    private String contractStatus;

    @Excel(name = "签署日期", databaseFormat = "yyyy-MM-dd", format = "yyyyMMdd")
    private String signDate;

}
