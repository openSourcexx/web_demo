package com.example.webdemo.sofaboot.common;

/**
 * todo
 *
 * @author 唐安全
 * @date 2020/10/09
 */
public class ClassLoaderUtils {
    /**
     * 得到当前ClassLoader，先找线程池的，找不到就找中间件所在的ClassLoader
     *
     * @return
     */
    public static ClassLoader getCurrentClassLoader() {
        ClassLoader cl = Thread.currentThread()
            .getContextClassLoader();
        if (cl == null) {
            cl = ClassLoaderUtils.class.getClassLoader();
        }
        return cl == null ? ClassLoader.getSystemClassLoader() : cl;
    }

    public static ClassLoader getClassLoader(Class<?> clazz) {
        ClassLoader loader = Thread.currentThread()
            .getContextClassLoader();
        if (loader != null) {
            return loader;
        }
        if (clazz != null) {
            loader = clazz.getClassLoader();
            if (loader != null) {
                return loader;
            }
        }
        return ClassLoader.getSystemClassLoader();
    }
}
