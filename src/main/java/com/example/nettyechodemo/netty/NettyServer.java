package com.example.nettyechodemo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NettyServer {

    private final int port = 8000;

    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    public void start() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .childHandler(new NettyServerInitializer());

            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("🚀 Netty Server 启动成功，端口：{}", port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("Netty 启动异常", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @PreDestroy
    public void shutdown() {
        log.info("Netty Server 正在关闭...");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
