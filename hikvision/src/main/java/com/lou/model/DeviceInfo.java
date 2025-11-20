package com.lou.model;

public class DeviceInfo {

    private String ipAddress;
    private String macAddress;
    private String deviceName;
    private String firmwareVersion;

    // getters and setters
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getMacAddress() { return macAddress; }
    public void setMacAddress(String macAddress) { this.macAddress = macAddress; }

    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public String getFirmwareVersion() { return firmwareVersion; }
    public void setFirmwareVersion(String firmwareVersion) { this.firmwareVersion = firmwareVersion; }

    @Override
    public String toString() {
        return String.format("IP: %s, MAC: %s, 设备名: %s, 版本: %s",
                ipAddress, macAddress, deviceName, firmwareVersion);
    }
}
