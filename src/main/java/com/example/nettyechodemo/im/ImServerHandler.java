package com.example.nettyechodemo.im;

import com.example.nettyechodemo.netty.ChatMessage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ImServerHandler extends SimpleChannelInboundHandler<ChatMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMessage msg) {
        Channel channel = ctx.channel();

        switch (msg.getType()) {
            case 1: // 登录
                String username = msg.getContent();
                UserChannelManager.addUser(username, channel);
                System.out.println("【用户登录】" + username);
                ctx.writeAndFlush(new ChatMessage((byte) 1, "欢迎你，" + username + "！"));
                break;
            case 2: // 聊天
                String sender = UserChannelManager.getUsername(channel);
                String broadcast = "【" + sender + "】：" + msg.getContent();
                for (Channel ch : UserChannelManager.getAllChannels()) {
                    ch.writeAndFlush(new ChatMessage((byte) 2, broadcast));
                }
                break;
            case 3: // 心跳
                System.out.println("收到心跳: " + channel.remoteAddress());
                break;
            default:
                System.out.println("未知消息类型：" + msg.getType());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        UserChannelManager.removeUser(ctx.channel());
        System.out.println("【用户下线】" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        UserChannelManager.removeUser(ctx.channel());
        ctx.close();
    }
}
