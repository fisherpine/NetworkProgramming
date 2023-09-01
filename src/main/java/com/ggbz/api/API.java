package com.ggbz.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class API {
    public static void main(String[] args) throws UnknownHostException {
        //获取本机的InetAddress对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);//DESKTOP-MTSMV5R/192.168.1.228

        //根据指定主机名 获取InetAddress对象
        InetAddress host1 = InetAddress.getByName("DESKTOP-MTSMV5R");
        System.out.println("host1="+host1);//host1=DESKTOP-MTSMV5R/192.168.1.228

        //3.根据域名返回 InetAddress对象，比如www.baidu.com
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println("host2="+host2);//host2=www.baidu.com/104.193.88.77

        //4.通过 InetAddress 对象，获取对应的地址
        String hostAddress = host2.getHostAddress();
        System.out.println("host2 对应的ip="+hostAddress);//host2 对应的ip=104.193.88.123

        //5.通过InetAddress对象，获取对应的主机名/或者域名
        String hostName = host2.getHostName();
        System.out.println("host2对应的主机名/域名="+hostName);//host2对应的主机名/域名=www.baidu.com
    }
}
