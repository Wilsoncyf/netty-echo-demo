package com.example.nettyechodemo;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class FastClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        OutputStream out = socket.getOutputStream();

        for (int i = 1; i <= 3; i++) {
            String msg = "Message_" + i;
            out.write(msg.getBytes(StandardCharsets.UTF_8));
            // 不 flush，故意造成堆积，模拟粘包
            Thread.sleep(10);
        }

        out.flush(); // 一起发出去，可能会粘包
        socket.close();
    }
}
