package com.example.nettyechodemo.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private byte type;      // 消息类型：1 登录、2 聊天、3 心跳
    private String content; // 内容，可为用户名、聊天内容等
}
