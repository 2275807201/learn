package com.liangh.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;

/**
 * 实现功能:
 *  1.客户端，正常的收发消息
 *  2.服务端：
 *      2.1 正常启动监听消息
 *      2.2 监听成功后可以收发消息，先收到消息，收到消息后回复消息{"已收到：" + content + ", 谢谢"}
 * @version V1.0
 * @description:
 * @author: liangh
 * @create: 2021/01/08 09:40
 */
@Slf4j
public class MyNetty {

    String ip = "127.0.0.1";
    int port = 28080;

    public static void main(String[] args) throws Exception{
        MyNetty myNetty = new MyNetty();

        // 启动服务端
        myNetty.startServer();

//        // 启动客户端
//        myNetty.startClient();
    }

    /**
     * 启动服务器
     */
    private void startServer() throws Exception {

        // 创建多路复用器
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();

        // 创建服务器启动器
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        ChannelFuture bindFuture = serverBootstrap.group(nioEventLoopGroup)
                .channel(NioServerSocketChannel.class)  // 服务端channel
                .handler(new ChannelInitializer<NioServerSocketChannel>() { // 类似SeverSocketChannel 创建的处理
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        log.info("查看socketChannel类型: {}", ch);
                    }
                })
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // SocketChannel = ServerSocketChannel.accept()后的socketChannel的处理
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                        // 获取客户端管道
                        ChannelPipeline pipeline = ch.pipeline();

                        // 管道添加读处理器
                        pipeline.addLast(new MyServerInHandler());
                    }
                }).bind(ip, port);

        bindFuture.channel().closeFuture().sync();
    }

    /**
     * 启动客户端
     * @throws Exception
     */
    private void startClient() throws Exception {

        // 创建eventLoop
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();

        // 客户端启动器
        Bootstrap bootstrap = new Bootstrap();

        // 连接
        ChannelFuture connect = bootstrap.group(nioEventLoopGroup)  // 指定eventLoop
                .channel(NioSocketChannel.class)    // 指定客户端channel
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 获取客户端管道
                        ChannelPipeline pipeline = ch.pipeline();

                        // 管道添加读处理器
                        pipeline.addLast(new MyClientInHandler());
                    }
                })
                .connect(ip, port);

        // 连接等待
        connect.sync();

        // 客户端channel
        Channel clientChannel = connect.channel();

        // 待发送消息
        String msg = "hello";

        // 创建byteBuf
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(128);
        // byteBuf写内容
        byteBuf.writeBytes(msg.getBytes());

        // 客户端发送消息
        ChannelFuture sendChannelFuture = clientChannel.writeAndFlush(byteBuf);
        // 发送等待
        sendChannelFuture.sync();
        log.info("消息已经发送：{}", msg);

        // 关闭通知同步
        clientChannel.closeFuture().sync();
    }
}

/**
 * 客户端读处理器
 */
@Slf4j
class MyClientInHandler extends ChannelInboundHandlerAdapter {

    /***
     * 重写读方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 将消息转换成byteBuf
        ByteBuf byteBuf = (ByteBuf) msg;

        // 读取消息byteBuf
        log.info("接收到的消息:{}", byteBuf.readCharSequence(byteBuf.readableBytes(), Charset.forName("utf-8")));
    }
}

/**
 * 服务端度处理器
 */
@Slf4j
class MyServerInHandler extends ChannelInboundHandlerAdapter {

    /***
     * 重写读方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        // 将消息转换成byteBuf
        ByteBuf byteBuf = (ByteBuf) msg;

        // 读取内容
        CharSequence content = byteBuf.readCharSequence(byteBuf.readableBytes(), Charset.forName("utf-8"));

        // 读取消息byteBuf
        log.info("接收到的消息:{}", content);

        // byteBuf写内容
        String repyMsg = "已收到：" + content + ", 谢谢";
        byteBuf.writeBytes(repyMsg.getBytes());

        // 回复消息
        ctx.writeAndFlush(byteBuf);
    }
}
