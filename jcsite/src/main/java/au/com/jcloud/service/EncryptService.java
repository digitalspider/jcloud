package au.com.jcloud.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by david.vittor on 8/08/16.
 */
public class EncryptService extends BaseService {

	public String md5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			LOG.error(e, e);
		}
		return null;
	}
}
