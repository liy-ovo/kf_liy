package com.baizhi.util;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

public class NoUtil {
    /**
     * 获取订单编号
     * @return
     */
    public static String getNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
        Date date = new Date();
        String f = sdf.format(date);
        char[] code = "0123456789".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(code[new Random().nextInt(code.length)]);
        }
        return f+stringBuilder.toString();
    }

    /**
     * 获取电脑物理地址MAC
     * @param b
     * @return
     */

    public static String hexByte(byte b) {
        String s = "000000" + Integer.toHexString(b);
        return s.substring(s.length() - 2);
    }

    public static String getMAC() {
        Enumeration<NetworkInterface> el;
        String mac_s = "";
        try {
            el = NetworkInterface.getNetworkInterfaces();
            while (el.hasMoreElements()) {
                byte[] mac = el.nextElement().getHardwareAddress();
                if (mac == null)
                    continue;
                mac_s = hexByte(mac[0]) + "-" + hexByte(mac[1]) + "-"
                        + hexByte(mac[2]) + "-" + hexByte(mac[3]) + "-"
                        + hexByte(mac[4]) + "-" + hexByte(mac[5]);
                System.out.println(mac_s + "MAC地址");

            }
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
        return mac_s;
    }

}
