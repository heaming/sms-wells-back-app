package com.kyowon.sms.wells.web.kakaosync.zcommon.kakaosyncutils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256Util {
    private static String iv;
    private static Key keySpec;

    /**
     * 16자리의 키값을 입력하여 객체를 생성한다.
     *
     * @param key
     * 암/복호화를 위한 키값
     * @throws UnsupportedEncodingException
     * 키값의 길이가 16이하일 경우 발생
     */
    private String key; // = "0123456789ABCDEFGHIJKLMNabcSdefg";

    /**
     * AES256 Key 채번 및 전역변수 할당
     * 주석문처리 - SonarQube 에러 해결 회피용
     * TODO: 메소드의 모듈화 필요 (decrypt의 key 채번 부분)
     * 2023-09-05 / 문호환
     */

    /*	public AES256Util() throws UnsupportedEncodingException {
    		this(">#$(]9!@;Z\"|k]}<");
    		//this(key);
    	}


    public AES256Util(String key) throws UnsupportedEncodingException {
        this.key = "KYOWN-biztalk_12";
        AES256Util.iv = key.substring(0, 16);
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        keySpec = new SecretKeySpec(keyBytes, "AES");
    }*/

    /**
     * AES256 으로 암호화 한다.
     *
     * @param str
     * 암호화할 문자열
     * TODO: 현재 사용 X (카카오에서 암호화 되어서 오기 때문) 추후 필요시 주석 해제
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    /*	public String encrypt(String str) throws NoSuchAlgorithmException,
    			GeneralSecurityException, UnsupportedEncodingException {
    		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
    		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
    		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));


    		String enStr = new String(Base64.encodeBase64URLSafe(encrypted));
    		return enStr;
    	}*/

    /**
     * AES256으로 암호화된 txt 를 복호화한다.
     * TODO: CBC 복호화 방식 GCM 복호화로 변경 필요 (Agent CBC 암호화 부분 수정 요청,, 요망)
     * @param str
     * 복호화할 문자열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    public static String decrypt(String str) throws NoSuchAlgorithmException,
        GeneralSecurityException, UnsupportedEncodingException {

        String key = "D8V0Rrqx6LQ7sYsn";
        AES256Util.iv = key.substring(0, 16);
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

        //Base64.encodeBase64URLSafe(str.getBytes())
        byte[] byteStr = Base64.decodeBase64(str.getBytes());
        return new String(c.doFinal(byteStr), "UTF-8");
    }

    /**
    *
    * Obejct Type 을 String Type으로 Casting하여 Return한다.
    * @param value
    * @return java.lang.String
    */
    public static String castString(Object value) {
        String out = null;
        if (value == null || "".equals(value)) {
            out = "";
        } else if (value instanceof Double) {
            out = new DecimalFormat("#0.0#################").format(value);
        } else {
            out = value.toString();
        }
        return out;
    }

}
