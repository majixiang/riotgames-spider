package com.riotgamesdemo.spider.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.protocol.HttpContext;

/**
 * @date 2021/1/27 21:25
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
public class TimeoutRetryStrategy implements ServiceUnavailableRetryStrategy {
    private final int maxRetries;
    private final long retryInterval;

    TimeoutRetryStrategy(int maxRetries, long retryInterval) {
        this.maxRetries = maxRetries;
        this.retryInterval = retryInterval;
    }

    @Override
    public boolean retryRequest(HttpResponse httpResponse, int executionCount, HttpContext httpContext) {
        return executionCount <= this.maxRetries
                && httpResponse.getStatusLine().getStatusCode() != 200
                && !(
                httpResponse.getStatusLine().getStatusCode() >= 500
                        && httpResponse.getStatusLine().getStatusCode() < 600);
    }

    @Override
    public long getRetryInterval() {
        return this.retryInterval;
    }
}
