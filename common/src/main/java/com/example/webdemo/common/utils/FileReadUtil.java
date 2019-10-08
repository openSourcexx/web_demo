package com.example.webdemo.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.webdemo.common.constant.SymbolConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件读取
 */
@Slf4j
@Component
public class FileReadUtil {
    /*
     * 本地路径
     */
    @Value("${FileRead.LOCAL_PATH}")
    public static final String LOCAL_PATH = "E://";

    public List<String> readFile(String fileName) {
        String path = LOCAL_PATH + "/" + fileName;
        log.error("解析文件:{}", path);
        return buildData(new File(path));
    }

    /**
     * 单批读取文件数据
     *
     * @param srcFile
     * @return
     */
    private List<String> buildData(File srcFile) {

        String s;
        String line;
        List<String> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(srcFile));
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                s = new String(line.getBytes(StandardCharsets.ISO_8859_1.name()), StandardCharsets.UTF_8.name())
                    + "|-1";
                list.add(s);
            }
        } catch (IOException e) {
        }
        // log.debug("下载文件{}返回列表:{}", srcFile.getPath(), list);
        return list;
    }

    /**
     * todo taq
     * 分批读取文件，每次默认1k 1组
     *
     * @param srcFile : 服务器本地文件
     * @return HashMap
     * k:Integer 当期数据组数,
     * v:List<List<String> 数据列,默认1k条
     */
    private Map<Integer, List<String>> buildDataBatch(File srcFile) {
        log.error("解析文件:{}", srcFile.getPath());
        int readLine = 1000;
        String s;
        String line;
        Map<Integer, List<String>> map = new HashMap<>();
        int key = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(srcFile));
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                s = new String(line.getBytes(StandardCharsets.ISO_8859_1.name()), StandardCharsets.UTF_8.name());
                List<String> groupList = map.get(key);
                if (groupList == null) {
                    groupList = new ArrayList<>(1000);
                }
                groupList.add(s);
                map.put(key, groupList);
                if (groupList.size() % readLine == 0) {
                    key++;
                    groupList = new ArrayList<>(1000);
                    map.put(key, groupList);
                }
            }
        } catch (IOException e) {
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        write();
    }

    private static void write() throws IOException {
        File file = new File(LOCAL_PATH + "bb.txt");
        int i = 1;
        String a = "1119090609385278216";
        StringBuilder sb;
        FileOutputStream os = new FileOutputStream(file);
        while (i <= 500000) {
            sb = new StringBuilder();
            sb.append(a + i);
            sb.append(SymbolConstants.VERTICAL_LINE);
            sb.append(i);
            sb.append(SymbolConstants.VERTICAL_LINE);
            sb.append("20190809");
            sb.append(SymbolConstants.VERTICAL_LINE);
            sb.append(i);
            sb.append(SymbolConstants.VERTICAL_LINE);
            sb.append(i);
            sb.append(SymbolConstants.VERTICAL_LINE);
            sb.append(i);
            sb.append(SymbolConstants.VERTICAL_LINE);
            sb.append(i);
            sb.append("\n");
            os.write(sb.toString()
                .getBytes());
            i++;
        }
        os.close();
    }

}
