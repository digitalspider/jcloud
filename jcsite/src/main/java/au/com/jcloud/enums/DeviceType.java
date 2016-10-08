package au.com.jcloud.enums;

public enum DeviceType {

    PC("PC", "Desktop"), MOBILE("MOBILE", "Mobile"), TABLET("PC", "Tablet");

    private final String localRepresentation;
    private final String deviceType;

    DeviceType(String localRepresentation , String deviceName) {
        this.localRepresentation = localRepresentation;
        this.deviceType = deviceName;
    }

    public String getLocalRepresentation() {
        return this.localRepresentation;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public static DeviceType parse(String deviceName) {
        for (DeviceType deviceType : values()) {
            if (deviceType.getDeviceType().equalsIgnoreCase(deviceName)) {
                return deviceType;
            }
        }
        return null;
    }
}