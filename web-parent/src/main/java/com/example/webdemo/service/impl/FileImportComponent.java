package com.example.webdemo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.example.webdemo.common.exception.ServiceException;
import com.example.webdemo.common.utils.DateUtil;
import com.example.webdemo.easypoi.ExcelDemo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import lombok.extern.slf4j.Slf4j;

/**
 * @author safe
 * @date 2021/2/19
 */
@Component
@Slf4j
public class FileImportComponent {

    /**
     * 读取excel
     *
     * @param bytes excel文件内容
     * @param clazz excel对象
     * @param type XSSF(.xlsx)/ HSSF(.xls)
     * @return
     */
    public <T> List<T> readFromExcel(byte[] bytes, Class<T> clazz, ExcelType type) {
        InputStream in = new ByteArrayInputStream(bytes);
        List<T> dataList = new ArrayList<>();
        Workbook workbook = null;
        try {
            if (ExcelType.XSSF == type) {
                workbook = new XSSFWorkbook(in);
            } else {
                workbook = new HSSFWorkbook(in);
            }

            Field[] fields = clazz.getDeclaredFields();
            // 类注解order->beanName
            Map<String, Field> orderMap = new HashMap<>();
            for (Field field : fields) {
                Excel annotation = field.getAnnotation(Excel.class);
                if (annotation != null) {
                    String order = annotation.orderNum();
                    orderMap.put(order, field);
                    field.setAccessible(true);
                }
            }
            // 默认读取第一行
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                T t = clazz.newInstance();
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String cellVal = getCellVal(cell);
                    if (StringUtils.isBlank(cellVal)) {
                        continue;
                    }
                    Field field = orderMap.get(String.valueOf(j));
                    handleField(t, field, cellVal);
                }
                dataList.add(t);
            }
        } catch (Exception e) {
            log.error("读取excel失败[{}]", e.getMessage(), e);
            throw new ServiceException(e.getMessage());
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.error("workbook 关闭异常", e);
                }
            }
        }
        return dataList;
    }

    private <T> void handleField(T t, Field field, String value) throws Exception {
        Class<?> type = field.getType();
        if (type == void.class || StringUtils.isBlank(value)) {
            return;
        }
        if (type == Object.class) {
            field.set(t, value);
        } else if (type.getSuperclass() == null || type.getSuperclass() == Number.class) {
            if (type == int.class || type == Integer.class) {
                field.set(t, NumberUtils.toInt(value));
            } else if (type == long.class || type == Long.class) {
                field.set(t, NumberUtils.toLong(value));
            } else if (type == byte.class || type == Byte.class) {
                field.set(t, NumberUtils.toByte(value));
            } else if (type == short.class || type == Short.class) {
                field.set(t, NumberUtils.toShort(value));
            } else if (type == double.class || type == Double.class) {
                field.set(t, NumberUtils.toDouble(value));
            } else if (type == float.class || type == Float.class) {
                field.set(t, NumberUtils.toFloat(value));
            } else if (type == char.class || type == Character.class) {
                field.set(t, CharUtils.toChar(value));
            } else if (type == boolean.class) {
                field.set(t, BooleanUtils.toBoolean(value));
            } else if (type == BigDecimal.class) {
                field.set(t, new BigDecimal(value));
            }
        } else if (type == Boolean.class) {
            field.set(t, BooleanUtils.toBoolean(value));
        } else if (type == Date.class) {
            field.set(t, DateUtil.getDate(value));
        } else if (type == String.class) {
            field.set(t, value);
        } else {
            Constructor<?> constructor = type.getConstructor(String.class);
            field.set(t, constructor.newInstance(value));
        }
    }

    private String getCellVal(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return BigDecimal.valueOf(cell.getNumericCellValue())
                .toString();
        } else if (cell.getCellType() == CellType.STRING) {
            return StringUtils.trimToEmpty(cell.getStringCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            return StringUtils.trimToEmpty(cell.getCellFormula());
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.ERROR) {
            return "ERROR";
        } else {
            return cell.toString()
                .trim();
        }
    }

    public static void main(String[] args) throws Exception {
        byte[] bytes = FileUtils.readFileToByteArray(new File("c:/tmp/t1.xlsx"));
        FileImportComponent fileImportComponent = new FileImportComponent();
        List<ExcelDemo> list = fileImportComponent.readFromExcel(bytes, ExcelDemo.class, ExcelType.XSSF);
        System.out.println();
    }
}
