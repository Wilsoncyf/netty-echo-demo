package com.example.nettyechodemo.client;

import com.example.nettyechodemo.netty.ChatMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHandler extends SimpleChannelInboundHandler<ChatMessage> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 连接建立后，发送消息
        System.out.println("连接成功，发送消息中...");
        ctx.writeAndFlush(new ChatMessage((byte) 1, "user123")); // 登录
        ctx.writeAndFlush(new ChatMessage((byte) 2, "你好，我是 user123")); // 聊天
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMessage msg) {
        System.out.println("客户端收到回应: 类型 = " + msg.getType() + ", 内容 = " + msg.getContent());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
