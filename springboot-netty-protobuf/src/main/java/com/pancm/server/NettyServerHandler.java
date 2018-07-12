package com.pancm.server;

import org.springframework.stereotype.Service;

import com.pancm.protobuf.UserInfo;
import com.pancm.protobuf.UserInfo.UserMsg;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;

/**
 * 
 * @Title: NettyServerHandler
 * @Description: 服务端业务逻辑
 * @Version:1.0.0
 * @author pancm
 * @date 2017年10月8日
 */
@Service("nettyServerHandler")
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

	/** 空闲次数 */
	private int idle_count = 1;
	/** 发送次数 */
	private int count = 1;


	/**
	 * 建立连接时，发送一条消息
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
		UserInfo.UserMsg userMsg = UserInfo.UserMsg.newBuilder().setId(1).setAge(18).setName("xuwujing").setState(0)
				.build();
		ctx.writeAndFlush(userMsg);
		super.channelActive(ctx);
	}

	/**
	 * 超时处理 如果5秒没有接受客户端的心跳，就触发; 如果超过两次，则直接关闭;
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
		if (obj instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) obj;
			if (IdleState.READER_IDLE.equals(event.state())) { // 如果读通道处于空闲状态，说明没有接收到心跳命令
				System.out.println("已经5秒没有接收到客户端的信息了");
				if (idle_count > 1) {
					System.out.println("关闭这个不活跃的channel");
					ctx.channel().close();
				}
				idle_count++;
			}
		} else {
			super.userEventTriggered(ctx, obj);
		}
	}

	/**
	 * 业务逻辑处理
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("第" + count + "次" + ",服务端接受的消息:" + msg);
		try {
			// 如果是protobuf类型的数据
		  if (msg instanceof UserMsg) {
				UserInfo.UserMsg userState = (UserInfo.UserMsg) msg;
				if (userState.getState() == 1) {
					System.out.println("客户端业务处理成功!");
				} else if(userState.getState() == 2){
					System.out.println("接受到客户端发送的心跳!");
				}else{
					System.out.println("未知命令!");
				}
			} else {
				System.out.println("未知数据!" + msg);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ReferenceCountUtil.release(msg);
		}
		count++;
	}

	/**
	 * 异常处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
