package com.example.webdemo.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * @author admin
 * @date 2021/1/24
 */
public class HessianSerializer {
    public HessianSerializer() {
    }

    public static <T> byte[] serialize(T obj) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput output = new HessianOutput(os);
        try {
            output.writeObject(obj);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return os.toByteArray();
    }

    public static <T> Object deserialize(byte[] bytes) {
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        HessianInput input = new HessianInput(is);
        try {
            return input.readObject();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
