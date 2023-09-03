package com.ggbz.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPFileUploadServer {
    public static void main(String[] args) throws IOException {
        //服务器在本机监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("本机监听8888接口");
        //等待连接
        Socket socket = serverSocket.accept();

        //读取客户端发送的术
        // 通过Socket得到输入流
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //将得到bytes 数组，写入到指定的路径，就得到一个文件了
        String destFilePath = "src\\a.jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
        bos.write(bytes);

        //向客户端发送收到图片
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("Server端收到图片");
        bufferedWriter.flush();
        socket.shutdownOutput();

        //关闭服务端
        bufferedWriter.close();
        bis.close();
        bos.close();
        socket.close();
        serverSocket.close();
    }
}
