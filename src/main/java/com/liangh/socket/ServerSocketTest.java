package com.liangh.socket;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2020/12/09 16:10
 */
public class ServerSocketTest {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(2000);

        for (;;){
            Socket socket = serverSocket.accept();
            new Thread(() -> {

                try {
                    // 第一次获取输出流
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("liangh is cool".getBytes());

//                    outputStream.close();
                    BufferedOutputStream bos = new BufferedOutputStream(outputStream);
                    bos.flush();
//                    bos.close();

                    // 再一次获取输出流
//                    outputStream = socket.getOutputStream();
//                    outputStream.write("second word".getBytes());
//                    outputStream.flush();
//                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }).start();
        }

    }


}
