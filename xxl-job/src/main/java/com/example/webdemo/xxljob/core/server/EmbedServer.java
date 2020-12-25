package com.example.webdemo.xxljob.core.server;

import com.example.webdemo.common.utils.GsonUtil;
import com.example.webdemo.common.utils.ThreadPoolUtils;
import com.example.webdemo.common.utils.ThrowableUtil;
import com.example.webdemo.xxljob.core.biz.ExecutorBiz;
import com.example.webdemo.xxljob.core.biz.impl.ExecutorBizImpl;
import com.example.webdemo.xxljob.core.biz.model.LogParam;
import com.example.webdemo.xxljob.core.biz.model.ReturnT;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 内嵌服务器
 *
 * @author tangaq
 * @date 2020/12/22
 */
public class EmbedServer {
    private static final Logger logger = LoggerFactory.getLogger(EmbedServer.class);

    private ExecutorBiz executorBiz;
    private Thread thread;

    public void start(final int port, String token) {
        executorBiz = new ExecutorBizImpl();
        thread = new Thread(() -> {
            // NioEventGroup用来处理IO操作的多线程事件循环器
            // boss用来接收进来的连接
            EventLoopGroup boosGroup = new NioEventLoopGroup();
            // work用来处理已经被接收的连接
            EventLoopGroup workGroup = new NioEventLoopGroup();

            ThreadPoolExecutor bizThreadPool = ThreadPoolUtils.buildDefaultThreadPool(200,
                    "EmbedServer ThreadPool-");

            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                bootstrap.group(workGroup, boosGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel channel) throws Exception {
                                channel.pipeline()
                                        // 心跳机制，当连接的空闲事件太长，将会触发一个IdleStateEvent事件，然后通过ChannelInboundHandler中重写的方法处理该事件
                                        .addLast(new IdleStateHandler(0, 0, 30 * 3, TimeUnit.SECONDS))
                                        // HttpServerCodec包含HttpRequestDecoder和HttpResponseEncoder
                                        // HttpServerCodec针对netty编解码的处理类,但这个只能获取http get请求的参数,即url后带？
                                        .addLast(new HttpServerCodec())
                                        // HttpObjectAggregator解决netty的处理器post请求，把HttpMessage和HttpContent聚合成一个FullHttpRequest或者FullHttpResponse
                                        .addLast(new HttpObjectAggregator(5 * 1024 * 1024))
                                        .addLast(new EmbedHttpServerHandler(executorBiz, token, bizThreadPool));
                            }
                        })
                        // 对于socket选项中的SO_KEEPALIVE,该参数用于设置TCP连接，用于可能长时间没有数据交流的连接。如果在2h内没有数据的通信时，TCP会自动发送一个活动探测数据报文
                        .childOption(ChannelOption.SO_KEEPALIVE, true);

                // 绑定服务器端口
                ChannelFuture future = bootstrap.bind(port)
                        .sync();

                logger.info("remoting server start success,netty type {},port {}", EmbedServer.class, port);

                // registry
                // ExecutorRegistryThread#registry();

                /*
                    主线程执行到这里就wait子线程结束，子线程才是真正监听和接受请求的，closeFuture()是开启一个channel的监听器，
                    负责监听channel是否关闭的状态，如果监听到channel关闭了，子线程才会释放
                 */
                future.channel().closeFuture().sync();

            } catch (Exception e) {
                if (e instanceof InterruptedException) {
                    logger.info(">>>>>>>>>>> xxl-job remoting server stop.");
                } else {
                    logger.error(">>>>>>>>>>> xxl-job remoting server error.", e);
                }
            } finally {
                // stop
                try {
                    workGroup.shutdownGracefully();
                    boosGroup.shutdownGracefully();
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void stop() {
        // stop registry
        // ExecutorRegistryThread#stop();

        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
        logger.info(">>>>>>>>>>>EmbedServer stop.ok");
    }

    /**
     * netty http
     */
    public static class EmbedHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
        private static final Logger logger = LoggerFactory.getLogger(EmbedHttpServerHandler.class);

        private ExecutorBiz executorBiz;
        private String accessToken;
        private ThreadPoolExecutor bizThreadPool;

        public EmbedHttpServerHandler(ExecutorBiz executorBiz, String accessToken, ThreadPoolExecutor bizThreadPool) {
            this.executorBiz = executorBiz;
            this.accessToken = accessToken;
            this.bizThreadPool = bizThreadPool;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
            String requestData = msg.content().toString(CharsetUtil.UTF_8);
            String uri = msg.uri();
            HttpMethod httpMethod = msg.method();
            boolean keepAlive = HttpUtil.isKeepAlive(msg);
            String token = msg.headers().get("ACCESS-TOKEN");

            bizThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // do business
                    Object responseObj = process(httpMethod, uri, token, requestData);

                    String json = GsonUtil.obj2Json(responseObj);

                    writeResponse(ctx, keepAlive, json);
                }

                private Object process(HttpMethod httpMethod, String uri, String token, String requestData) {
                    if (HttpMethod.POST != httpMethod) {
                        return new ReturnT<String>(ReturnT.FAIL_CODE, "invalid request, HttpMethod not support.");
                    }

                    if (StringUtils.isBlank(uri)) {
                        return new ReturnT<String>(ReturnT.FAIL_CODE, "invalid request, url-mapping empty.");
                    }

                    try {
                        if ("/log".equals(uri)) {
                            LogParam param = GsonUtil.json2Obj(requestData, LogParam.class);
                            return executorBiz.log(param);
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        return new ReturnT<String>(ReturnT.FAIL_CODE, "request error:" + ThrowableUtil.toString(e));
                    }
                    return ReturnT.FAIL;
                }

                private void writeResponse(ChannelHandlerContext ctx, boolean keepAlive, String json) {
                    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(json, CharsetUtil.UTF_8));

                    response.headers()
                            .set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");       // HttpHeaderValues.TEXT_PLAIN.toString()
                    response.headers()
                            .set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
                    if (keepAlive) {
                        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                    }
                    ctx.writeAndFlush(response);
                }
            });
        }

        /**
         * 没有重写这个方法 远程主机强迫关闭了一个现有的连接
         *
         * @param ctx
         * @throws Exception
         */
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            logger.error(">>>>>>>>>>>>> xxl-job netty_http server caught exception", cause);
            ctx.close();
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateHandler) {
                ctx.channel().close();
                logger.debug(">>>>>>>>>>> xxl-job provider netty_http server close an idle channel.");
            } else {
                super.userEventTriggered(ctx, evt);
            }
        }

        public ExecutorBiz getExecutorBiz() {
            return executorBiz;
        }

        public void setExecutorBiz(ExecutorBiz executorBiz) {
            this.executorBiz = executorBiz;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public ThreadPoolExecutor getBizThreadPool() {
            return bizThreadPool;
        }

        public void setBizThreadPool(ThreadPoolExecutor bizThreadPool) {
            this.bizThreadPool = bizThreadPool;
        }
    }
}
