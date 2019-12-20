package by.future.common.utils;

import org.apache.axis.encoding.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;

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


	/**
	 * 小程序AES解密 PKCS7Padding
	 *
	 * @Author：by@Deng
	 * @Date：2018/6/26 13:32
	 */
	public static String decryptApplet(String content, String key,String iv) throws BadPaddingException {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(org.apache.commons.codec.binary.Base64.decodeBase64(key), "AES");

			AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
			params.init(new IvParameterSpec(org.apache.commons.codec.binary.Base64.decodeBase64(iv)));

			byte[] decryptContent = org.apache.commons.codec.binary.Base64.decodeBase64(content);

			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(Cipher.DECRYPT_MODE, keySpec, params);

			byte[] original = cipher.doFinal(decryptContent);

			return new String(original, "utf-8");

		}  catch (BadPaddingException badPaddingException){
			throw badPaddingException;
		} catch (Exception var8) {
			return null;
		}

	}

}
