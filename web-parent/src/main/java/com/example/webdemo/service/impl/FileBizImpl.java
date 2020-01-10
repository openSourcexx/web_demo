package com.example.webdemo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.webdemo.service.biz.FileBiz;

/**
 * 文件处理
 *
 * @author tangaq
 * @since 2019/9/20 14:58
 */
@Service
public class FileBizImpl implements FileBiz {
    @Override
    public void upload(MultipartFile file) {
        return;
    }

    @Override
    public byte[] download(String fileName) {
        return new byte[0];
    }
}
