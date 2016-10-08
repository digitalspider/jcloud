package au.com.jcloud.service;

import org.apache.commons.collections4.map.PassiveExpiringMap;

import java.util.Map;

import au.com.jcloud.enums.FormType;

/**
 * Created by david.vittor on 31/08/16.
 */

public class FormSubmissionCountService extends BaseService {

	// counts expire every 1min
	private static final Map<String, Integer> countMap = new PassiveExpiringMap<String, Integer>(1 * 60 * 1000);

	private String getCountKey(String ipAddress, FormType type) {
		return ipAddress.toLowerCase()+"|"+type.name().toLowerCase();
	}

	public void increment(String ipAddress, FormType type) {
		String key = getCountKey(ipAddress, type);
		Integer count = countMap.get(key);
		if (count==null) {
			count = 1;
		}
		countMap.put(key,++count);
	}

	public int get(String ipAddress, FormType type) {
		String key = getCountKey(ipAddress, type);
		if (countMap.containsKey(key)) {
			return countMap.get(key);
		}
		return 0;
	}

	public void allow(String ipAddress, FormType type) throws Exception {
		int value = get(ipAddress, type);
		LOG.debug("allow ip="+ipAddress+" type="+type.name()+" value="+value);
		if (value > type.getMaxAttempts()) {
			throw new Exception("Too many " + type.name().toLowerCase() + " attempts from ip=" + ipAddress);
		}
	}
}
