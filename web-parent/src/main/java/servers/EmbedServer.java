package servers;

import com.example.webdemo.common.utils.Demo;
import com.example.webdemo.common.utils.GsonUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author tangaq
 * @date 2021/1/29
 */
public class EmbedServer {
    public static void main(String[] args) {
        new EmbedServer().start();
    }

    public void start() {
        EventLoopGroup work = new NioEventLoopGroup();
        EventLoopGroup boss = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(work, boss)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new IdleStateHandler(0, 0, 30 * 3, TimeUnit.SECONDS))
                                .addLast(new HttpServerCodec())
                                .addLast(new HttpObjectAggregator(5 * 1024 * 1024))
                                .addLast(new HttpRequestChannelHandler());
                    }
                });

        try {
            ChannelFuture future = bootstrap.bind(9900).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    private class HttpRequestChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
            String body = msg.content().toString(CharsetUtil.UTF_8);
            String uri = msg.uri();
            HttpMethod method = msg.method();
            Demo demo = new Demo();
            demo.setName(body);
            demo.setC(new Date());
            // new Thread(new Runnable() {
            //     @Override
            //     public void run() {
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(GsonUtil.obj2Json(demo), CharsetUtil.UTF_8));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            ctx.writeAndFlush(response);
            //     }
            // });

        }
    }
}
