package com.riotgamesdemo.spider.client;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/**
 * @date 2021/1/24 23:01
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
public class RiotGamesKeepAliveHandler extends DefaultConnectionKeepAliveStrategy {
    @Override
    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        long keepAlive = super.getKeepAliveDuration(httpResponse, httpContext);
        if (keepAlive == -1) {
            //如果服务器没有设置keep-alive这个参数，我们就把它设置成10分钟
            keepAlive = 600000;
        }
        return keepAlive;
    }
}
