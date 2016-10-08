package au.com.jcloud.enums;

/**
 * Created by david on 31/08/16.
 */

public enum FormType {
	LOGIN(3),
	REGISTRATION(1),
	COMMENT(2);

	private int maxAttempts;

	FormType(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}
}
