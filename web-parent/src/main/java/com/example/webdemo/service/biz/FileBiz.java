package com.example.webdemo.service.biz;

import org.springframework.web.multipart.MultipartFile;

import com.example.webdemo.vo.request.BatchImportRequest;
import com.example.webdemo.vo.response.BatchImportResp;

/**
 * 文件
 *
 * @author tangaq
 * @since 2019/9/20 14:58
 */
public interface FileBiz {
    /**
     * 上传
     *
     * @param file
     */
    String upload(MultipartFile file);

    /**
     * 下载文件
     *
     * @param fileName
     * @return
     */
    byte[] download(String fileName);

    BatchImportResp batchImport(BatchImportRequest request);
}
