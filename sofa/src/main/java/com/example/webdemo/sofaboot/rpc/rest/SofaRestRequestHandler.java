package com.example.webdemo.sofaboot.rpc.rest;

import org.jboss.resteasy.plugins.server.netty.NettyHttpRequest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * todo
 *
 * @author 唐安全
 * @date 2020/10/09
 */
public class SofaRestRequestHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof NettyHttpRequest) {
            NettyHttpRequest request = (NettyHttpRequest) msg;
            //            if (EventBus.)
        }
    }
}
