package com.example.nettyechodemo;

import com.example.nettyechodemo.netty.NettyServer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NettyServerBootstrap {

    private final NettyServer nettyServer;

    @PostConstruct
    public void init() {
        // Netty 在一个新线程中启动，防止阻塞 Spring Boot 主线程
        new Thread(nettyServer::start).start();
    }
}
