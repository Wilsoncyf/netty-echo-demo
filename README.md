# Netty Echo Demo

🚀 基于 Spring Boot + Netty 的入门级 Echo 服务项目。

本项目用于学习和演示 Netty 的核心概念，包括：
- Netty 服务端启动流程
- Pipeline 和 Handler 的使用
- 粘包/拆包问题演示
- 基于分隔符的解码器
- Spring Boot 自动集成 Netty 服务

---

## 📁 项目结构

```
src/
├── main/java/com/example/nettyechodemo
│   ├── NettyEchoDemoApplication.java        # Spring Boot 启动入口
│   ├── NettyServerBootstrap.java            # Netty 自动启动逻辑
│   ├── netty/
│       ├── NettyServer.java                 # Netty 服务端配置
│       ├── NettyServerInitializer.java      # 初始化 Pipeline
│       ├── EchoServerHandler.java           # Echo 业务逻辑
│   ├── FastClient.java                      # 模拟粘包客户端
│   ├── BreakClient.java                     # 模拟拆包客户端
```
---

## 🧪 启动方式

### 启动服务端：

```bash
mvn spring-boot:run
```

或直接运行 `NettyEchoDemoApplication.java`。

服务启动后监听端口：`localhost:8000`

---

### 测试方式：

你可以使用 `telnet` 或 Netty 自带客户端发送消息测试。

```bash
telnet localhost 8000
```

或者运行 `FastClient` / `BreakClient` 模拟粘包/拆包现象。

---

## 🔧 技术栈

- Java 17
- Spring Boot 3.4.4
- Netty 4.1.x
- Maven

---

## 📘 学习参考

- Netty 官方文档：https://netty.io/
- Spring Boot：https://spring.io/projects/spring-boot

---

## 📌 作者

👤 [@Wilsoncyf](https://github.com/Wilsoncyf)

欢迎 Star / Fork / Issue 🙌
```