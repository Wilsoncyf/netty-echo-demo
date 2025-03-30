package com.example.nettyechodemo.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
//        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(
//                1024, Unpooled.copiedBuffer("$_", CharsetUtil.UTF_8)));
//        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
//        ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
//        ch.pipeline().addLast(new EchoServerHandler());
        ch.pipeline().addLast(new MessageDecoder());
        ch.pipeline().addLast(new MessageEncoder());
        ch.pipeline().addLast(new CustomMessageHandler()); // 处理 ChatMessage 对象

    }
}
