package com.mmg.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;

/**
 * Created by yj on 2017/6/29.
 */
public class CipherUtil {
    private static final Log logger = LogFactory.getLog(CipherUtil.class);
    private static final String Algorithm = "DESede";
    private static final String keyStr = "sa4df/;j2l';1fsad5f3r8.m";

    public static String threeDesEncrypt(String original){
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keyStr.getBytes(), Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] bytes = c1.doFinal(original.getBytes());
            return new String(Base64.encode(bytes));
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static String threeDesDecrypt(String cipher){
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keyStr.getBytes(), Algorithm);
            //base64解码
            byte[] bytes = Base64.decode(cipher.getBytes());
            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return new String(c1.doFinal(bytes));
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        String encrypt = threeDesEncrypt("aaasadfasdasdfsdfasdfasdfsfdf");
//        String decrypt = threeDesDecrypt(encrypt);
//        System.out.println(encrypt);
//        System.out.println(decrypt);

        long startTimemillis = System.currentTimeMillis();
        long endTimemillis = startTimemillis + (60 * 60 * 24 * 14*1000);
        System.out.println(startTimemillis);
        System.out.println(endTimemillis);
        System.out.println(new Timestamp(startTimemillis));
        System.out.println(new Timestamp(endTimemillis));
    }
}
