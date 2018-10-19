package by.future.common.utils;

import java.net.*;
import java.util.Enumeration;

/**
 * ip工具类
 *
 * @Author：by@Deng
 * @Date：2018/9/4 14:32
 */
public class IpUtils {


    /**
     * 获取本地ip，兼容linux
     *
     * @Author：by@Deng
     * @Date：2018/9/4 14:34
     */
    public static String getLocalIp(){
        String ip = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {

                NetworkInterface networkInterface = en.nextElement();

                String name = networkInterface.getName();
                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses(); addressEnumeration.hasMoreElements();) {

                        InetAddress inetAddress = addressEnumeration.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            String ipAddress = inetAddress.getHostAddress();
                            if (!ipAddress.contains("::") && !ipAddress.contains("0:0:") && !ipAddress.contains("fe80")) {
                                ip = ipAddress;
                            }
                        }

                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("获取ip地址异常");
            e.printStackTrace();
        }
        return ip;
    }




}
