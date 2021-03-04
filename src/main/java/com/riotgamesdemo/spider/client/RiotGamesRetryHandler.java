package com.riotgamesdemo.spider.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * @date 2021/1/17 17:48
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Slf4j
public class RiotGamesRetryHandler implements HttpRequestRetryHandler {
    private int MAX_RETRIES;

    public RiotGamesRetryHandler(Integer maxRetrys) {
        super();
        this.MAX_RETRIES = maxRetrys;
    }

    @Override
    public boolean retryRequest(IOException exception, int retryTimes, HttpContext httpContext) {
        if (retryTimes >= MAX_RETRIES) {
            log.error("Reached max retry times, won't retry anymore.");
            return false;
        }

        if (exception != null) {
            log.warn("Exception {}, retry search request: {}", exception, retryTimes);
            return true;
        } else {
            log.error("Unknown error: the exception is null.");
        }

        return false;
    }
}
