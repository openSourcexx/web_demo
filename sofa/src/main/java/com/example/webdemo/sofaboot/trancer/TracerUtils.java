package com.example.webdemo.sofaboot.trancer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.commons.lang3.StringUtils;

/**
 * TracerUtils
 *
 * @author tanganquan
 * @date 2020/09/27
 */
public class TracerUtils {
    private static String P_ID_CACHE = null;

    public static String getInetAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress address = null;
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    address = addresses.nextElement();
                    if (!address.isLoopbackAddress() && !address.getHostAddress()
                        .contains(":")) {
                        return address.getHostAddress();
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return
     */
    public static String getPID() {
        if (P_ID_CACHE != null) {
            return P_ID_CACHE;
        }
        String processName = java.lang.management.ManagementFactory.getRuntimeMXBean()
            .getName();
        if (StringUtils.isBlank(processName)) {
            return StringUtils.EMPTY;
        }
        String[] processSplitName = processName.split("@");
        if (processName.length() == 0) {
            return StringUtils.EMPTY;
        }

        String pid = processSplitName[0];
        if (StringUtils.isBlank(pid)) {
            return StringUtils.EMPTY;
        }
        P_ID_CACHE = pid;
        return pid;
    }
}
