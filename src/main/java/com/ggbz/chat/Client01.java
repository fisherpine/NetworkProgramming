package com.ggbz.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client01 {
    public static void main(String[] args) {
        try {
            // 连接到服务器
            Socket socket = new Socket("localhost", 9999);
            System.out.println(socket );
            // 创建输出流，用于向服务器发送消息
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            // 创建输入流，用于接收服务器消息
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // 创建从控制台输入消息的Scanner
            Scanner scanner = new Scanner(System.in);

            // 启动一个线程来接收服务器的消息并打印到控制台
            //这个线程的存在允许客户端能够同时发送消息和接收服务器的消息，而不会由于等待服务器的响应而被阻塞。
            Thread receiveThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println("服务器回复: " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            while (true) {

                String clientMessage = scanner.nextLine();

                if ("exit".equalsIgnoreCase(clientMessage)) {
                    break; // 输入 'exit' 退出循环
                }

                writer.println("01:"+clientMessage);
                //println(clientMessage): 这个方法将 clientMessage 变量的内容写入到输出流，并在消息的末尾添加一个换行符 \n。
                // 这样做是为了确保消息在发送给服务器后会被视为一个完整的消息，服务器可以根据换行符来分割不同的消息
            }

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
