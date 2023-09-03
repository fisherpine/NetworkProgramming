package com.ggbz.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 */
public class SocketTCPServer {
    public static void main(String[] args) throws IOException {
        //  1. 在本机的XXX端口监听，等待连接
        //  细节：要求本机，9999端口没有被占用
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端正在监听本机9999端口");

        //  2. 在没有客户端连接XXX端口时，程序会阻塞，等待连接
        //  如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("socket="+socket.getClass());

        //  3. 通过socket.getInputStream()读取客户端写入到数据通道的数据，并显示
        InputStream inputStream = socket.getInputStream();

        //  4.IO读取
        byte[] buf = new byte[1024];
        int readLen = 0;
        while((readLen = inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,readLen));//根据读取到的实际长度，显示实际内容
        }

        //   5.关闭流和socket
        inputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务端关闭");
    }
}
