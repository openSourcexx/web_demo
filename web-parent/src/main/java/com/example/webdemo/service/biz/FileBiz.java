package com.example.webdemo.service.biz;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件
 *
 * @author tangaq@yunrong.cn
 * @since 2019/9/20 14:58
 */
public interface FileBiz {
    /**
     * 上传
     *
     * @param file
     */
    void upload(MultipartFile file);

    /**
     * 下载文件
     *
     * @param fileName
     * @return
     */
    byte[] download(String fileName);
}
