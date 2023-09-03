package com.ggbz.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReturnMessageSocketTCPServer {
    public static void main(String[] args) throws IOException {
        //监听9999端口
        ServerSocket serverSocket = new ServerSocket(9999);
        //阻塞，等待连接，得到socket
        Socket socket = serverSocket.accept();
        //得到输入流，回显数据
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while((readLen = inputStream.read(buf)) != -1){
            System.out.println(new String(buf,0,readLen));
        }
        //回应客户端
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,Client".getBytes());
        //设置写入结束标记
        socket.shutdownOutput();
        //关闭服务端
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
