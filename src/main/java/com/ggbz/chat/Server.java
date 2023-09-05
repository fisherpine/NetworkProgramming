package com.ggbz.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    private static List<ClientHandler> clients = new ArrayList<>();

    private static List<Socket> sockets = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 监听9999端口
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("监听本机9999端口");

        // 创建一个线程用于Server发送消息
        Thread adminThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String ServerMessage = scanner.nextLine();
                sendAdminMessage(ServerMessage);
            }
        });
        adminThread.start();

        while (true) {
            // 阻塞，等待连接，得到socket
            Socket socket = serverSocket.accept();
            System.out.println("有新的客户端连接服务器："+socket);
            // 创建新线程来处理客户端连接
            ClientHandler clientHandler = new ClientHandler(socket, socket.toString());

            clients.add(clientHandler);

            Thread clientThread = new Thread(clientHandler);
            clientThread.start();

        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter writer;
        private String clientName;

        public ClientHandler(Socket socket,String clientName) {
            this.socket = socket;
            this.clientName = clientName;
        }

        public void run() {
            try {
                // 得到输入流，回显数据
                InputStream inputStream = socket.getInputStream();
                // IO读取，使用字符流，使用InputStreamReader 将inputStream 转成字符流
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String clientMessage;

                // 创建输出流，用于向客户端发送消息
                OutputStream outputStream = socket.getOutputStream();
                writer = new PrintWriter(outputStream, true);

                while ((clientMessage = bufferedReader.readLine()) != null) {
                    System.out.println(clientMessage);
                }
            } catch (IOException e) {
                System.err.println("客户端连接异常: " + e.getMessage());
            } finally {
                try {
                    // 关闭客户端连接
                    socket.close();
                } catch (IOException e) {
                    System.err.println("无法关闭客户端连接: " + e.getMessage());
                }
            }
        }

        public void sendMessage(String message) {
            writer.println(message);
        }
    }

    private static void sendAdminMessage(String message) {
        for (ClientHandler client : clients) {
            String desport = new String(String.valueOf(client.socket.getPort()));
            String[] split = message.split(":");
            String port = split[0];
            String mes = split[1];
            if(desport.equals(port)) {
                client.sendMessage(mes);
            }else if(port.equals("")){
                client.sendMessage(mes);
            }
        }
    }
}
