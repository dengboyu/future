package by.future.common.utils;

import org.apache.axis.encoding.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

	private final static String VECTOR = "09,.34ajoydfuEEi";
	private final static String ENCODEKEY = "-!@QWaszx#^GDFUN";

	public static String encrypt(String cleartext) throws Exception {
		return encrypt(cleartext, ENCODEKEY, VECTOR);
	}

	public static String encrypt(String cleartext, String encKey) throws Exception {
		return encrypt(cleartext, encKey, VECTOR);
	}

	/**
	 * 方法描述 Aes加密算法
	 * 
	 * @param cleartext
	 * @param key
	 * @param vi
	 * @return
	 * @throws Exception
	 * @author chhou @date 2015年10月26日 上午10:30:49
	 */
	public static String encrypt(String cleartext, String key, String vector) throws Exception {
		byte[] keyBytes = key.getBytes("ASCII");
		byte[] vi = vector.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(vi);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(cleartext.getBytes());
		return Base64.encode(encrypted);
	}

	public static String decrypt(String enc, String key) throws Exception {
		return decrypt(enc, key, VECTOR);
	}

	public static String decrypt(String enc) throws Exception {
		return decrypt(enc, ENCODEKEY, VECTOR);
	}

	/**
	 * 方法描述 Aes解密算法
	 * 
	 * @throws Exception
	 * @author chhou @date 2015年10月26日 上午10:30:49
	 */
	public static String decrypt(String enc, String key, String vector) throws Exception {
		byte[] keyBytes = key.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "AES");
		byte[] vi = vector.getBytes();
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(vi);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] encrypted = Base64.decode(enc);
		byte[] decrypted = cipher.doFinal(encrypted);
		return new String(decrypted);
	}

}
