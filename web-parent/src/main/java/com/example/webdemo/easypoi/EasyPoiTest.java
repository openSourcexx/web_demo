package com.example.webdemo.easypoi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.pdf.PdfExportUtil;
import cn.afterturn.easypoi.pdf.entity.PdfExportParams;

/**
 * HSSF(97-2003)
 * XSSF(2007 .xlsx)
 */
public class EasyPoiTest {
    public static void main(String[] args) throws Exception {
        // exportXls();
        exportXlsx();
        // exportMoreSheet();
        // excelExportPdf();
    }

    /**
     * 单sheet导出
     *
     * @throws IOException
     */
    private static void exportXls() {
        ExportParams exportParams = new ExportParams();
        List<ExcelDemo> list = getData();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ExcelDemo.class, list);
        output(workbook, "t1");
    }

    private static void exportXlsx() {
        ExportParams exportParams = new ExportParams();
        // todo
        exportParams.setType(ExcelType.XSSF);
        List<ExcelDemo> list = getData();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ExcelDemo.class, list);
        output(workbook, "t1");
    }

    private static List<ExcelDemo> getData() {
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
        return list;
    }

    private static void excelExportPdf() {
        PdfExportParams params = new PdfExportParams("测试");
        List<ExcelDemo> list = getData();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:/tmp/" + "xxx" + ".pdf");
            PdfExportUtil.exportPdf(params, ExcelDemo.class, list, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void output(Workbook workbook, String fileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("D:/tmp/" + fileName + ".xlsx");
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
