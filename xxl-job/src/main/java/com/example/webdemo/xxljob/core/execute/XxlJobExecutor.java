package com.example.webdemo.xxljob.core.execute;

import com.example.webdemo.common.utils.IPUtil;
import com.example.webdemo.xxljob.core.server.EmbedServer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tangaq
 * @date 2020/12/23
 */
@Data
public class XxlJobExecutor implements Serializable {
    private static final long serialVersionUID = 8489630874745142277L;
    private String adminAddresses;
    private String accessToken;
    private String appname;
    private String address;
    private String ip;
    private int port;
    private String logPath;
    private int logRetentionDays;

    private EmbedServer embedServer;

    public void start() throws Exception {
        // 初始化netty server
        initEmbedServer(address, ip, appname, accessToken);
    }

    private void initEmbedServer(String address, String ip, String appname, String accessToken) {
        ip = (ip != null && ip.trim().length() > 0) ? ip : IPUtil.getIp();

        // generate address
        if (address == null || address.trim().length() == 0) {
            String ipPortAddress = IPUtil.getIpPort(ip, port);   // registry-address：default use address to registry
            // , otherwise use ip:port if address is null
            address = "http://{ip_port}/".replace("{ip_port}", ipPortAddress);
        }

        embedServer = new EmbedServer();
        embedServer.start(port, null);
    }

    public void destroy() {
        if (embedServer != null) {
            embedServer.stop();
        }
    }
}
