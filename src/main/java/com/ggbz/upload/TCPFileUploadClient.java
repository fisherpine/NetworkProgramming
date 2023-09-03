package com.ggbz.upload;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPFileUploadClient {
    public static void main(String[] args) throws IOException {
        //客户端连接
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        //创建读取磁盘文件的输入流
        String filePath = "E:\\a.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

        //bytes 就是filePath对应的字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //通过socket获取到输出流，将bytes数据发送给服务端
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);//将文件对应的字节数组的内容，写入到数据通道
        socket.shutdownOutput();//设置写入数据的结束

        //接受服务端发来的消息
        InputStream inputStream = socket.getInputStream();
        //使用StreamUtils的方法，直接将inputStream读取到的内容 转成字符串
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);

        //关闭客户端
        inputStream.close();
        bis.close();
        socket.close();
        System.out.println("关闭客户端");
    }


}
