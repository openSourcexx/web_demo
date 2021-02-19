package com.example.webdemo.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.webdemo.beans.ImportInfo;
import com.example.webdemo.common.exception.BizException;
import com.example.webdemo.common.utils.sequence.SequenceTool;
import com.example.webdemo.dao.intf.ImportInfoDao;
import com.example.webdemo.easypoi.ExcelDemo;
import com.example.webdemo.service.biz.FileBiz;
import com.example.webdemo.vo.request.BatchImportRequest;
import com.example.webdemo.vo.response.BatchImportResp;

import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

/**
 * 文件处理
 *
 * @author tangaq
 * @since 2019/9/20 14:58
 */
@Service
public class FileBizImpl implements FileBiz {
    @Autowired
    private FileImportComponent fileImportComponent;
    @Autowired
    private ImportInfoDao importInfoDao;

    @Override
    public String upload(MultipartFile file) {
        return null;
    }

    @Override
    public byte[] download(String filePath) {
        return new byte[0];
    }

    @Override
    public BatchImportResp batchImport(BatchImportRequest request) {
        BatchImportResp resp = new BatchImportResp();
        String filePath = request.getFilePath();
        ExcelType type;
        if (filePath.endsWith(".xlsx")) {
            type = ExcelType.XSSF;
        } else if (filePath.endsWith(".xls")) {
            type = ExcelType.HSSF;
        } else {
            throw new BizException("不支持的文件类型");
        }
        // 下载文件检查是否存在
        byte[] bytes;
        try {
            bytes = FileUtils.readFileToByteArray(new File("c:/tmp/t1.xlsx"));
        } catch (IOException e) {
            throw new BizException(e.getMessage(), e);
        }
        List<ExcelDemo> dataList = fileImportComponent.readFromExcel(bytes, ExcelDemo.class, type);
        if (CollectionUtils.isEmpty(dataList)) {
            throw new BizException("导入文件为空");
        }
        // 新增导入流水
        String importId = SequenceTool.nextId();
        ImportInfo importInfo = new ImportInfo();
        importInfo.setImportId(importId);
        importInfo.setSerialNo(request.getBizSerialNo());
        importInfo.setTotalCount(dataList.size());
        importInfoDao.insertSelective(importInfo);
        // 汇总执行结果
        // 更新导入结果

        return resp;
    }
}
