package com.liangh.socket;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/**
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2020/12/09 16:16
 */
@Slf4j
public class SocketTest {

    public static void main(String[] args) throws Exception {

//        Socket socket = new Socket("127.0.0.1", 2000);

        InetAddress address = InetAddress.getLocalHost();
        Socket socket = new Socket("127.0.0.1", 2000, address, 18081);

        byte [] a = new byte[1024];

        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
//        for (;;){
            int len = bis.read(a);
            log.info("输入流读取结果：{}", new String(a,0, len));
//        }


    }

}
