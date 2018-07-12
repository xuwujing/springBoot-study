package com.pancm.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 
 * @Title: NettyServer
 * @Description: Netty服务端
 * @Version:1.0.0
 * @author pancm
 * @date 2017年10月8日
 */
@Service("nettyServer")
public class NettyServer {
	private static final int port = 9876; // 设置服务端端口
	private static EventLoopGroup boss = new NioEventLoopGroup(); // 通过nio方式来接收连接和处理连接
	private static EventLoopGroup work = new NioEventLoopGroup(); // 通过nio方式来接收连接和处理连接
	private static ServerBootstrap b = new ServerBootstrap();
	
	@Autowired
	private NettyServerFilter nettyServerFilter;
	
	
	public void run() {
		try {
			b.group(boss, work);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(nettyServerFilter); // 设置过滤器
			// 服务器绑定端口监听
			ChannelFuture f = b.bind(port).sync();
			System.out.println("服务端启动成功,端口是:" + port);
			// 监听服务器关闭监听
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 关闭EventLoopGroup，释放掉所有资源包括创建的线程
			work.shutdownGracefully();
			boss.shutdownGracefully();
		}
	}

}
