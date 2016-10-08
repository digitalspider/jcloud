package au.com.jcloud.service;

import java.util.Base64;
import java.util.Map;

import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.apache.commons.lang3.StringUtils;

import au.com.jcloud.util.ConvertUtil;

/**
 * Created by david.vittor on 17/08/16.
 */
public class TokenService extends BaseService {
	// counts expire every 30min
	private static final Map<String, String> tokenMap = new PassiveExpiringMap<String, String>(30 * 60 * 1000);

	public String generateAndRecordToken(String username) {
		String token = generateTimeBasedToken();
		tokenMap.put(username, token);
		return token;
	}

	public String generateTimeBasedToken() {
		long time = System.currentTimeMillis();
		Base64.Encoder enc = Base64.getEncoder();
		String token = enc.encodeToString(ConvertUtil.longToBytes(time));
		return token.substring(0, token.length() - 1); // remove = at the end
	}

	public void clearToken(String username) {
		if (StringUtils.isNotBlank(username) && tokenMap.containsKey(username)) {
			tokenMap.remove(username);
		}
	}

	public boolean validateToken(String username, String tokenToValidate) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(tokenToValidate)) {
			return false;
		}
		String token = tokenMap.get(username);
		if (StringUtils.isBlank(token)) {
			return false; // token has expired, or never existed
		}
		if (!token.equals(tokenToValidate)) {
			return false; // token is invalid
		}
		return true;
	}

	public static void main(String[] args) {
		TokenService tokenService = new TokenService();
		System.out.println("token=" + tokenService.generateTimeBasedToken());
	}
}
