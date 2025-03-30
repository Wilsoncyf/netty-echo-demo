package com.example.nettyechodemo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class MessageEncoder extends MessageToByteEncoder<ChatMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ChatMessage msg, ByteBuf out) {
        System.out.println("【编码器】正在编码消息: " + msg);
        byte[] contentBytes = msg.getContent().getBytes(StandardCharsets.UTF_8);
        int totalLength = 1 + contentBytes.length;

        out.writeInt(totalLength);      // 总长度 = type(1) + 内容
        out.writeByte(msg.getType());   // 消息类型
        out.writeBytes(contentBytes);   // 消息内容
    }
}
