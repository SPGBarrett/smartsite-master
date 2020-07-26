package cn.dofuntech.spconst.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import com.hs2e.common.lang.StringUtils;

public class AesEncryptUtils {
	/**
	* AES算法加密文本
	* @param secretKey 	密钥
	* @param originalString 	需要加密的文本
	* @return 		加密的文本(Base64格式)
	*
	**/
	public static String aesEncrypt(String secretKey, String originalString) {
	    if (StringUtils.isEmpty(secretKey))
	    	throw new IllegalArgumentException("this secretKey must not be empty");
	    if (StringUtils.isEmpty(originalString))
		throw new IllegalArgumentException("this originalString must not be empty");

	    final byte[] byteKey = toByteArray(secretKey);
	    SecretKeySpec skeySpec = new SecretKeySpec(byteKey, "AES");
	    try {
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] originalBytes = originalString.trim().getBytes("UTF-8");
		if (originalBytes.length % 16 != 0) {
		    byte[] newOriginalBytes = new byte[(originalBytes.length / 16 + 1) * 16];
		    System.arraycopy(originalBytes, 0, newOriginalBytes, 0, originalBytes.length);
		    originalBytes = newOriginalBytes;
		}

		final byte[] encryptedBytes = cipher.doFinal(originalBytes);
		return new String(Base64.encodeBase64(encryptedBytes));
		} catch (NoSuchAlgorithmException e) {
		    e.printStackTrace();
		} catch (NoSuchPaddingException e) {
		    e.printStackTrace();
		} catch (InvalidKeyException e) {
		    throw new IllegalArgumentException(secretKey + " is illegal," + e.getMessage());
		} catch (IllegalBlockSizeException e) {
		    throw new IllegalArgumentException("unable to encrypt '" + originalString + "'," + e.getMessage());
		} catch (BadPaddingException e) {
		    e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] toByteArray(String hexString) {
		if (StringUtils.isEmpty(hexString))
			throw new IllegalArgumentException("this hexString must not be empty");

		hexString = hexString.toLowerCase();
		final byte[] byteArray = new byte[hexString.length() / 2];
		int k = 0;
		for (int i = 0; i < byteArray.length; i++) {
			byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
			byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
			byteArray[i] = (byte) (high << 4 | low);
			k += 2;
		}
		return byteArray;
	}
}
