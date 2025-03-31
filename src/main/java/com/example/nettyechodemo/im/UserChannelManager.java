package com.example.nettyechodemo.im;

import io.netty.channel.Channel;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserChannelManager {

    private static final Map<String, Channel> userChannelMap = new ConcurrentHashMap<>();

    public static void addUser(String username, Channel channel) {
        userChannelMap.put(username, channel);
    }

    public static void removeUser(Channel channel) {
        userChannelMap.entrySet().removeIf(entry -> entry.getValue().equals(channel));
    }

    public static Collection<Channel> getAllChannels() {
        return userChannelMap.values();
    }

    public static String getUsername(Channel channel) {
        return userChannelMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(channel))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("未知用户");
    }
}
