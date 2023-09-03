package com.ggbz.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 */
public class SocketTCPClient {
    public static void main(String[] args) throws IOException {
        //  1. 连上服务端（IP，端口）
        // 解读：连接本机的9999端口
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        System.out.println("客户端 socket返回="+socket.getClass());
        //  2. 连接上后，生成Socket，使用socket.getOutputStream()
        //  得到 和 socket对象关联的输出流对象
        OutputStream outputStream = socket.getOutputStream();
        //  3. 通过输出流，写入数据到数据通道
        outputStream.write("hello,server".getBytes());
        //  4.关闭流对象和socket，必须关闭
        outputStream.close();
        socket.close();
        System.out.println("客户端退出");

    }
}
