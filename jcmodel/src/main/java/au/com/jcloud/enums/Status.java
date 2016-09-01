package au.com.jcloud.enums;

/**
 * Created by david.vittor on 14/08/16.
 */
public enum Status {
	ENABLED(1), DISABLED(0);

	Status(int value) {
		this.value = value;
	}

	private int value;

	public int getValue() {
		return value;
	}

	public static Status parse(int value) {
		for (Status status : values()) {
			if (status.value == value) {
				return status;
			}
		}
		return null;
	}
}
