package com.riotgamesdemo.spider.client;

import com.google.common.util.concurrent.RateLimiter;
import com.riotgamesdemo.spider.config.RiotGamesConfig;
import com.riotgamesdemo.spider.dao.AdminSettingRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @date 2021/1/16 20:34
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Slf4j
public class RiotGamesClient {
    // 100 / 120 ~= 0.83
    private static RateLimiter rateLimiterMin = RateLimiter.create(0.80);
    private static RateLimiter rateLimiterSec = RateLimiter.create(18);

    private static final int MAX_RETRIES = 5;
    private static final long RETRY_INTERVAL = 1000;

    private static final String UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36";
    private static final String AP = "*/*";
    private static final String AL = "zh-CN,zh;q=0.9";

    private String baseUrl;
    private String token;

    private HttpClient httpClient;


    protected RiotGamesClient(String baseUrl, String token) {
        this.baseUrl = baseUrl;
        this.token = token;
    }

    protected HttpClient getClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1000)
                .setConnectTimeout(1000).build();

        List<Header> headerList = new ArrayList<>(5);
        headerList.add(new BasicHeader(HttpHeaders.USER_AGENT, UA));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT, AP));
        headerList.add(new BasicHeader(HttpHeaders.ACCEPT_LANGUAGE, AL));
        headerList.add(new BasicHeader("Origin", baseUrl));
        headerList.add(new BasicHeader("X-Riot-Token", token));

        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setDefaultHeaders(headerList)
                .setKeepAliveStrategy(new RiotGamesKeepAliveHandler())
                .setRetryHandler(new RiotGamesRetryHandler(MAX_RETRIES))
                .setServiceUnavailableRetryStrategy(new TimeoutRetryStrategy(MAX_RETRIES, RETRY_INTERVAL))
                .build();
        return this.httpClient;
    }

    public void init() {
        if(httpClient == null) {
            getClient();
        }
    }

    public HttpResponse execute(HttpUriRequest httpRequest) throws IOException {
        log.info("current time : {} .", System.currentTimeMillis());
        rateLimiterMin.acquire();
        rateLimiterSec.acquire();
        log.info("current time : {} .", System.currentTimeMillis());
        return httpClient.execute(httpRequest);
    }


}
