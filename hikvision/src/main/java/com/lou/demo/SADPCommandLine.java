package com.lou.demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SADPCommandLine {

    /**
     * 调用海康威视官方SADP工具进行设备搜索
     */
    public List<DeviceInfo> searchUsingSADPTool() {
        List<DeviceInfo> devices = new ArrayList<>();

        try {
            // 方法1: 使用海康威视官方SADP工具
            String sadpPath = System.getProperty("user.dir") + "\\hikvision\\src\\main\\resources\\lib\\sadpdlg.exe";

            //String sadpPath = "C:\\Program Files (x86)\\Hikvision\\SADP\\SADP.exe";
            File sadpFile = new File(sadpPath);

            if (sadpFile.exists()) {
                System.out.println("使用官方SADP工具搜索...");
                Process process = Runtime.getRuntime().exec(sadpPath);
                System.out.println(process);
                // SADP工具会自动搜索并显示结果
                // 我们需要解析输出或使用其他方式获取结果
            } else {
                System.out.println("未找到官方SADP工具，尝试其他方法...");
                searchUsingCommandLine(devices);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return devices;
    }

    /**
     * 使用命令行工具进行网络设备发现
     */
    private void searchUsingCommandLine(List<DeviceInfo> devices) {
        try {
            // 方法2: 使用arp命令获取局域网设备
            System.out.println("使用ARP表搜索局域网设备...");
            Process process = Runtime.getRuntime().exec("arp -a");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "GBK") // Windows中文系统使用GBK
            );

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("动态") || line.contains("dynamic")) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length >= 2) {
                        String ip = parts[0];
                        String mac = parts[1];
                        if (isValidIP(ip) && isValidMAC(mac)) {
                            DeviceInfo device = new DeviceInfo();
                            device.setIpAddress(ip);
                            device.setMacAddress(mac);
                            device.setDeviceName("网络设备");
                            devices.add(device);
                        }
                    }
                }
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用nmap进行设备扫描（需要安装nmap）
     */
    public List<DeviceInfo> searchUsingNmap() {
        List<DeviceInfo> devices = new ArrayList<>();

        try {
            System.out.println("使用nmap扫描局域网...");

            // 获取本地IP段
            String localNetwork = getLocalNetwork();
            if (localNetwork != null) {
                Process process = Runtime.getRuntime().exec("nmap -sn " + localNetwork);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );

                String line;
                DeviceInfo currentDevice = null;

                while ((line = reader.readLine()) != null) {
                    if (line.contains("Nmap scan report for")) {
                        if (currentDevice != null) {
                            devices.add(currentDevice);
                        }
                        currentDevice = new DeviceInfo();
                        // 解析IP地址
                        String[] parts = line.split(" ");
                        if (parts.length >= 6) {
                            currentDevice.setIpAddress(parts[5]);
                        }
                    } else if (line.contains("MAC Address:")) {
                        if (currentDevice != null) {
                            String[] parts = line.split(" ");
                            if (parts.length >= 3) {
                                currentDevice.setMacAddress(parts[2]);
                            }
                        }
                    }
                }

                if (currentDevice != null) {
                    devices.add(currentDevice);
                }

                reader.close();
            }

        } catch (Exception e) {
            System.out.println("nmap扫描失败，请确保已安装nmap: " + e.getMessage());
        }

        return devices;
    }

    /**
     * 使用ping命令扫描局域网
     */
    public List<DeviceInfo> searchUsingPing() {
        List<DeviceInfo> devices = new ArrayList<>();
        String localIP = getLocalIP();

        if (localIP != null) {
            System.out.println("使用Ping扫描局域网: " + localIP);

            String networkPrefix = localIP.substring(0, localIP.lastIndexOf(".") + 1);

            // 使用多线程加速ping扫描
            List<Thread> threads = new ArrayList<>();

            for (int i = 1; i < 255; i++) {
                final String ip = networkPrefix + i;
                Thread thread = new Thread(() -> {
                    if (ping(ip)) {
                        DeviceInfo device = new DeviceInfo();
                        device.setIpAddress(ip);
                        device.setDeviceName("在线设备");
                        synchronized (devices) {
                            devices.add(device);
                        }
                        System.out.println("发现设备: " + ip);
                    }
                });
                threads.add(thread);
                thread.start();

                // 控制并发数量
                if (threads.size() >= 50) {
                    waitForThreads(threads);
                    threads.clear();
                }
            }

            waitForThreads(threads);
        }

        return devices;
    }

    private void waitForThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean ping(String ip) {
        try {
            Process process = Runtime.getRuntime().exec("ping -n 1 -w 1000 " + ip);
            int exitValue = process.waitFor();
            return exitValue == 0;
        } catch (Exception e) {
            return false;
        }
    }

    private String getLocalIP() {
        try {
            java.net.NetworkInterface networkInterface = java.net.NetworkInterface.getByInetAddress(
                    java.net.InetAddress.getLocalHost()
            );
            java.util.Enumeration<java.net.InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                java.net.InetAddress addr = addresses.nextElement();
                if (addr instanceof java.net.Inet4Address && !addr.isLoopbackAddress()) {
                    return addr.getHostAddress();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getLocalNetwork() {
        String localIP = getLocalIP();
        if (localIP != null) {
            String[] parts = localIP.split("\\.");
            return parts[0] + "." + parts[1] + "." + parts[2] + ".0/24";
        }
        return null;
    }

    private boolean isValidIP(String ip) {
        return ip.matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    }

    private boolean isValidMAC(String mac) {
        return mac.matches("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
    }

    public static void main(String[] args) {
        SADPCommandLine searcher = new SADPCommandLine();

        System.out.println("=== 开始搜索局域网设备 ===");

        // 方法1: 使用官方SADP工具
        List<DeviceInfo> devices = searcher.searchUsingSADPTool();

        // 方法2: 如果官方工具找不到，使用ping扫描
        if (devices.isEmpty()) {
            System.out.println("尝试使用Ping扫描...");
            devices = searcher.searchUsingPing();
        }

        // 方法3: 使用nmap扫描
        if (devices.isEmpty()) {
            System.out.println("尝试使用nmap扫描...");
            devices = searcher.searchUsingNmap();
        }

        // 显示结果
        if (devices.isEmpty()) {
            System.out.println("未发现任何设备");
        } else {
            System.out.println("\n=== 搜索完成，共发现 " + devices.size() + " 个设备 ===");
            for (int i = 0; i < devices.size(); i++) {
                System.out.println((i + 1) + ". " + devices.get(i));
            }
        }
    }
}

class DeviceInfo {
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
        return String.format("IP: %-15s MAC: %-17s 设备名: %s",
                ipAddress,
                macAddress != null ? macAddress : "未知",
                deviceName != null ? deviceName : "未知设备");
    }
}