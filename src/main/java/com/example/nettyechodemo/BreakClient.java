package com.example.nettyechodemo;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BreakClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8000);
        OutputStream out = socket.getOutputStream();

        String msg = "ThisIsALongMessage";
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);

        // 模拟拆包：一半先发
        out.write(Arrays.copyOfRange(bytes, 0, 5));
        Thread.sleep(100); // 故意延迟
        out.write(Arrays.copyOfRange(bytes, 5, bytes.length));

        out.flush();
        socket.close();
    }
}
