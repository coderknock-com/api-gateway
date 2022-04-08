package com.coderknock.apigateway.core.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 配置信息
 *
 * @author sanchan
 * @微信 sanchan_coderknock
 * @B站 https://space.bilibili.com/62450448
 * @CSDN https://blog.csdn.net/sanchan
 * @WebSite https://coderknock.com
 * @date 2022-04-08
 */
@Data
@Accessors(fluent = true)
public class ApiGateWayConfig {
    /**
     * 服务端配置信息
     */
    private Server server;

    @Data
    @Accessors(fluent = true)
    private class Server {
        /**
         * 端口
         */
        private int port = 8888;
    }
}
