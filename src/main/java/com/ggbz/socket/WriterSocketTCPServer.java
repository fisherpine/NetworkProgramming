package com.ggbz.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 字符流
 */
public class WriterSocketTCPServer {
    public static void main(String[] args) throws IOException {
        //监听9999端口
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("监听本机9999端口");
        //阻塞，等待连接，得到socket
        Socket socket = serverSocket.accept();
        //得到输入流，回显数据
        InputStream inputStream = socket.getInputStream();
        //IO读取，使用字符流，使用InputStreamReader 将inputStream 转成字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);//输出
        //回应客户端
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello client 字符流");
        bufferedWriter.newLine();// 插入一个换行符，表示回复内容的结束
        bufferedWriter.flush();
        //关闭服务端
        bufferedWriter.close();//关闭外层流
        bufferedReader.close();
        socket.close();
        serverSocket.close();
    }

}
