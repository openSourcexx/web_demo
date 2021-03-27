package com.example.webdemo.common.parse;

import java.net.URL;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import cn.hutool.core.io.FileUtil;

/**
 * @author mango
 * @date 2021/3/27
 */
public class LocalYmlParser {

    public void parse(String path) throws Exception {
        String content = FileUtil.readUtf8String(path);
        JSONObject ruleObj = convert2Json(content);
        JSONObject flowJsonObject = JSONObject.parseObject(ruleObj.toJSONString(), Feature.OrderedField);
        System.out.println(flowJsonObject);
    }

    private JSONObject convert2Json(String content) {
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(content);
        return new JSONObject(map);
    }

    public static void main(String[] args) throws Exception {
        LocalYmlParser p = new LocalYmlParser();
        URL resource = Thread.currentThread()
            .getContextClassLoader()
            .getResource("f2.yml");

        p.parse(resource.getPath());
    }
}
