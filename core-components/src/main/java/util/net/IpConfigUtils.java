package util.net;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import util.Chars;
import util.StringUtils;
import util.Strings;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class IpConfigUtils {

    public static final String SUBNET_MASK = "255.255.255.0";
    private static final String X_FORWARDED_FOR = "X-Forwarded-For";
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String IP_ADDRESS = getIpAddress(null);

    /**
     * 获取IP地址
     *
     * @return IP地址
     */
    public static String getIpAddress() {
        return IP_ADDRESS;
    }

    /**
     * 获取IP地址
     *
     * @param interfaceName 网卡名称
     * @return IP地址
     */
    public static String getIpAddress(String interfaceName) {
        try {
            List<String> ipList = getHostAddress(interfaceName);
            return CollectionUtils.isNotEmpty(ipList) ? ipList.get(0) : Strings.EMPTY;
        } catch (Exception ex) {
            return Strings.EMPTY;
        }
    }

    /**
     * 获取已激活网卡的IP地址
     *
     * @param interfaceName 网卡名称
     * @return 已激活网卡的IP地址
     * @throws SocketException 套接字异常
     */
    private static List<String> getHostAddress(String interfaceName) throws SocketException {
        List<String> ipList = new ArrayList<String>(5);
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            Enumeration<InetAddress> allAddress = networkInterface.getInetAddresses();
            while (allAddress.hasMoreElements()) {
                InetAddress address = allAddress.nextElement();
                if (address.isLoopbackAddress()) {
                    continue;
                }
                if (address instanceof Inet6Address) {
                    continue;
                }
                String hostAddress = address.getHostAddress();
                if (interfaceName == null) {
                    ipList.add(hostAddress);
                } else if (networkInterface.getDisplayName().equals(interfaceName)) {
                    ipList.add(hostAddress);
                }
            }
        }
        return ipList;
    }

    /**
     * 获取客户端请求的 IP 地址
     *
     * @param request
     * @return
     */
    public static String parseIpAddress(@NonNull HttpServletRequest request) {
        String ip = request.getHeader(X_FORWARDED_FOR);
        if (StringUtils.isEmpty(ip) || IpConfig.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (StringUtils.isEmpty(ip) || IpConfig.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (StringUtils.isEmpty(ip) || IpConfig.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取 MAC 地址
     *
     * @return
     * @throws SocketException
     * @throws UnknownHostException
     */
    public static String getMacAddress() throws SocketException, UnknownHostException {
        return getMacAddress(InetAddress.getLocalHost());
    }

    /**
     * 获取MAC地址
     *
     * @param inetAddress
     * @return
     * @throws SocketException
     */
    public static String getMacAddress(@NonNull InetAddress inetAddress) throws SocketException {
        byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append(Chars.MINUS);
            }
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }
        return sb.toString().toUpperCase();
    }


    /**
     * 判断两个 IP 是否在同一网段
     *
     * @param ip1        第一个 IP
     * @param ip2        第二个 IP
     * @param subnetMask 子网掩码
     * @return true 如果两个 IP 在同一网段，否则返回 false
     */
    public static boolean isSameSubnet(String ip1, String ip2, String subnetMask) throws UnknownHostException {
        InetAddress address1 = InetAddress.getByName(ip1);
        InetAddress address2 = InetAddress.getByName(ip2);
        InetAddress subnet = InetAddress.getByName(subnetMask);
        byte[] b1 = address1.getAddress();
        byte[] b2 = address2.getAddress();
        byte[] b3 = subnet.getAddress();
        for (int i = 0; i < b1.length; i++) {
            if ((b1[i] & b3[i]) != (b2[i] & b3[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个 IP 是否在同一网段
     *
     * @param ip1 第一个 IP
     * @param ip2 第二个 IP
     * @return true 如果两个 IP 在同一网段，否则返回 false
     */
    public static boolean isSameSubnet(String ip1, String ip2) throws UnknownHostException {
        return isSameSubnet(ip1, ip2, SUBNET_MASK);
    }
}
