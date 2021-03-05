package com.example.webdemo.sofaboot.common;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.fastjson.JSON;
import com.alipay.sofa.rpc.common.utils.FileUtils;

/**
 * 配置加载器和操作入口
 *
 * @author 唐安全
 * @date 2020/10/09
 */
public class RpcConfigs {
    /**
     * 全局配置
     */
    private final static ConcurrentMap<String, Object> CFG = new ConcurrentHashMap<>();

    private final static ConcurrentMap<String, List<RpcConfigListener>> CFG_LISTENER = new ConcurrentHashMap<>();

    static {
        init();
    }

    private static void init() {
        try {
            String json = FileUtils.file2String(RpcConfigs.class, "rpc-config-default.json", "UTF-8");
            Map map = JSON.parseObject(json, Map.class);
            CFG.putAll(map);
            loadCustom("sofa-rpc/rpc-config.json");
            CFG.putAll(new HashMap(System.getProperties()));// 注意部分属性可能被覆盖为字符串
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void loadCustom(String fineName) throws IOException {
        ClassLoader classLoader = com.example.sofaboot.common.ClassLoaderUtils.getClassLoader(RpcConfigs.class);
        Enumeration<URL> urls = classLoader != null ? classLoader.getResources(fineName) :
            ClassLoader.getSystemResources(fineName);
        if (urls != null) {

        }
    }

    public static boolean getBooleanValue(String primaryKey) {
        Object val = CFG.get(primaryKey);
        if (val == null) {
            throw new RuntimeException("异常");
        } else {
            return Boolean.valueOf(val.toString());
        }
    }

    /**
     * 配置变更会拿到通知
     *
     * @param <T>
     */
    public interface RpcConfigListener<T> {
        /**
         * On change
         *
         * @param oldV
         * @param newV
         */
        public void onChange(T oldV, T newV);
    }
}
