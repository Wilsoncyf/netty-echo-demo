package com.example.nettyechodemo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CustomMessageHandler extends SimpleChannelInboundHandler<ChatMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMessage msg) {
        switch (msg.getType()) {
            case 1:
                System.out.println("【登录】用户：" + msg.getContent());
                ctx.writeAndFlush(new ChatMessage((byte) 1, "登录成功：" + msg.getContent()));
                break;
            case 2:
                System.out.println("【聊天】内容：" + msg.getContent());
                ctx.writeAndFlush(new ChatMessage((byte) 2, "收到消息：" + msg.getContent()));
                break;
            case 3:
                System.out.println("【心跳】收到心跳包");
                break;
            default:
                System.out.println("【未知类型】type=" + msg.getType());
        }
    }
}
