package com.example.webdemo.easypoi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 *
 */
public class EasyPoiTest {
    public static void main(String[] args) throws Exception {
        exportOneSheet();
        // exportMoreSheet();
    }

    /**
     * 单sheet导出
     *
     * @throws IOException
     */
    private static void exportOneSheet() throws IOException {
        ExportParams exportParams = new ExportParams("测试", "表格1");

        List<ExcelDemo> list = new ArrayList<>();
        ExcelDemo a;
        for (int i = 1; i < 5; i++) {
            a = new ExcelDemo();
            a.setContractId("id" + i);
            a.setContractName("name" + i);
            a.setContractStatus("0");
            a.setContractType("loan");
            if (i % 2 == 0) {
                a.setContractType("credit");
            }
            a.setSignDate("2019-09-18");
            list.add(a);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ExcelDemo.class, list);
        output(workbook, "t1");
    }

    private static void output(Workbook workbook, String fileName) throws IOException {

        FileOutputStream fos = new FileOutputStream("D:/" + fileName + ".xls");
        workbook.write(fos);
        fos.close();
    }
}
