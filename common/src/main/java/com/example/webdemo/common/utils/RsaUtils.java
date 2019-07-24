package com.example.webdemo.common.utils;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;

import com.example.webdemo.common.constant.RsaConstants;

import javax.crypto.Cipher;
import lombok.extern.slf4j.Slf4j;

/**
 * Rsa 验签,加密,解密
 */
@Slf4j
public class RsaUtils {

    /**
     * 随机生成密钥对
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, Object> getGenKeyPair() throws NoSuchAlgorithmException {
        Map<String, Object> keyMap = new LinkedHashMap<>();
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RsaConstants.RSA);
        // 初始化密钥对生成器
        keyPairGenerator.initialize(RsaConstants.KEY_LENGTH);
        //公钥,私钥对象
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 公钥字符串
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        keyMap.put("privateKeyString", privateKeyString);
        keyMap.put("publicKeyString", publicKeyString);
        log.info("随机生成的秘钥对:{}", keyMap);
        return keyMap;
    }

    /**
     * 公钥加密
     *
     * @param dataStr: 需要加密的数据
     * @param publicKey: 公钥key
     * @return
     */
    public static byte[] encryptData(String dataStr, String publicKey) throws Exception {
        log.info("加密前数据:{}", dataStr);
        Cipher cipher = Cipher.getInstance(RsaConstants.RSA);
        PublicKey pubKey = loadPublicKey(publicKey);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] data = dataStr.getBytes(StandardCharsets.UTF_8.name());
        int keyBit = getKeySize(pubKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        int step = keyBit / 8 - 11;
        for (int i = 0; inputLen - offSet > 0; offSet = i * step) {
            byte[] cache;
            if (inputLen - offSet > step) {
                cache = cipher.doFinal(data, offSet, step);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            ++i;
        }
        byte[] bytes = out.toByteArray();
        out.close();
        String resultData = Base64.encodeBase64String(bytes);
        log.info("加密后数据:{}", resultData);
        return bytes;
    }

    /**
     * 公钥加密
     *
     * @param data: 需要加密的数据
     * @param publicKey: 公钥key
     * @return
     * @throws Exception
     */
    public static String encryptData(byte[] data, String publicKey) throws Exception {
        log.info("加密前数据:{}", data);
        Cipher cipher = Cipher.getInstance(RsaConstants.RSA);
        PublicKey pubKey = loadPublicKey(publicKey);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        int keyBit = getKeySize(pubKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        int step = keyBit / 8 - 11;
        for (int i = 0; inputLen - offSet > 0; offSet = i * step) {
            byte[] cache;
            if (inputLen - offSet > step) {
                cache = cipher.doFinal(data, offSet, step);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            ++i;
        }
        byte[] bytes = out.toByteArray();
        out.close();
        String resultData = Base64.encodeBase64String(bytes);
        log.info("加密后数据:{}", resultData);
        return resultData;
    }

    /**
     * 私钥解密
     *
     * @param data: 解密数据
     * @param privateKey: 私钥key
     */
    public static String decryptData(String data, String privateKey) throws Exception {
        log.info("解密前数据:{}", data);
        byte[] encryptedData = java.util.Base64.getDecoder()
            .decode(data.getBytes(StandardCharsets.UTF_8.name()));
        // 解密
        PrivateKey priKey = loadPrivateKey(privateKey);
        Cipher cipher = Cipher.getInstance(RsaConstants.RSA);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        int keyBit = getKeySize(priKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        int step = keyBit / 8;
        for (int i = 0; inputLen - offSet > 0; offSet = i * step) {
            byte[] cache;
            if (inputLen - offSet > step) {
                cache = cipher.doFinal(encryptedData, offSet, step);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            ++i;
        }
        byte[] resultByte = out.toByteArray();
        out.close();
        String resultData = new String(resultByte);
        log.info("解密后数据:{}", resultData);
        return resultData;
    }

    /**
     * 验证签名
     *
     * @param data: 加密数据
     * @param sign: 签名
     * @param publicKey: 公钥key
     * @return
     * @throws Exception
     */
    public static boolean checkSign(String data, String sign, String publicKey) throws Exception {
        log.info("验证数据:{},签名:{}", data, sign);
        byte[] dataBytes = java.util.Base64.getDecoder()
            .decode(data.getBytes(StandardCharsets.UTF_8.name()));
        byte[] signBytes = java.util.Base64.getDecoder()
            .decode(sign.getBytes(StandardCharsets.UTF_8.name()));
        PublicKey pubKey = loadPublicKey(publicKey);
        Signature signature = Signature.getInstance(RsaConstants.SIGN_ALGORITHMS);
        signature.initVerify(pubKey);
        signature.update(dataBytes);
        boolean b = signature.verify(signBytes);
        log.info("验证结果:{}", b);
        return b;
    }

    /**
     * 使用私钥对数据进行加密签名
     *
     * @param encryptByte 数据
     * @param privateKey 私钥
     * @return 加密后的签名
     */
    public static String rsaSign(byte[] encryptByte, String privateKey) {
        try {
            Signature signature = Signature.getInstance(RsaConstants.SIGN_ALGORITHMS);
            PrivateKey priKey = loadPrivateKey(privateKey);
            signature.initSign(priKey);
            signature.update(encryptByte);
            byte[] signed = signature.sign();
            return java.util.Base64.getEncoder()
                .encodeToString(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取私钥key
     *
     * @param publicKey
     * @return
     */
    public static int getKeySize(PublicKey publicKey) {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        return rsaPublicKey.getModulus()
            .bitLength();
    }

    /**
     * 获取公钥key长度
     *
     * @param privateKey
     * @return
     */
    public static int getKeySize(PrivateKey privateKey) {
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        return rsaPrivateKey.getModulus()
            .bitLength();
    }

    /**
     * 加载公钥
     *
     * @param publicKeyStr: 公钥key
     * @return
     * @throws Exception
     */
    private static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
        try {
            byte[] buffer = java.util.Base64.getDecoder()
                .decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(RsaConstants.RSA);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 加载私钥
     *
     * @param privateKeyStr: 私钥key
     * @return
     * @throws Exception
     */
    private static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = java.util.Base64.getDecoder()
                .decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(RsaConstants.RSA);
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

}
