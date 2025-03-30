package com.example.nettyechodemo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.charset.StandardCharsets;

public class MessageDecoder extends LengthFieldBasedFrameDecoder {

    public MessageDecoder() {
        super(1024, 0, 4, 0, 0);
        // 参数含义: maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println("【解码器】尝试解码...");
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) return null;

        int length = frame.readInt(); // 读取长度（其实没用，可不读）
        byte type = frame.readByte();
        byte[] contentBytes = new byte[frame.readableBytes()];
        frame.readBytes(contentBytes);
        String content = new String(contentBytes, StandardCharsets.UTF_8);

        System.out.println("【解码器】解码成功: type=" + type + ", content=" + content);
        return new ChatMessage(type, content);
    }
}
