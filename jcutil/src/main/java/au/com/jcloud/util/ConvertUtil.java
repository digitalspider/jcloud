package au.com.jcloud.util;

import java.nio.ByteBuffer;

/**
 * Created by david on 18/08/16.
 */
public class ConvertUtil {

	public static byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(x);
		return buffer.array();
	}

	public static long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes);
		buffer.flip(); // need flip
		return buffer.getLong();
	}
}
