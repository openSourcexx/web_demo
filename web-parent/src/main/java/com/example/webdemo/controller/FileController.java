package com.example.webdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.webdemo.service.biz.FileBiz;
import com.example.webdemo.vo.request.FileRequest;

/**
 * 文件处理
 *
 * @author tangaq
 * @since 2019/9/20 14:53
 */
@RestController
@RequestMapping("/api/file")
public class FileController {
    @Autowired
    private FileBiz fileBiz;

    @RequestMapping("/upload")
    public void upload(MultipartFile file) {
        fileBiz.upload(file);
    }

    @RequestMapping("/download")
    public byte[] download(@RequestBody FileRequest fileRequest) {
        return fileBiz.download(fileRequest.getFileName());
    }
}
