package com.ggbz.socket;

import java.io.IOException;
import java.io.InputStream;
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
        //设置写入结束标记
        socket.shutdownOutput();
        //  4. 获取和socket关联的输入流，读取数据（字节）并显示
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while((readLen = inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,readLen));
        }
        //  5.关闭流对象和socket，必须关闭
        outputStream.close();
        socket.close();
        System.out.println("客户端退出");

    }
}
