package com.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by snow on 2015/8/23.
 * SHA1加密转换
 */
public class SecurityUtils {

    public static String shal(String str) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("sha1");
            md.update(str.getBytes());
            byte[] msg = md.digest();
            for(byte b : msg) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
