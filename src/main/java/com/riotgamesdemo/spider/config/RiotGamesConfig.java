package com.riotgamesdemo.spider.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @date 2021/1/17 1:30
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Configuration
@Getter
public class RiotGamesConfig {
    @Value("${spider.riotgames.apikey}")
    private String apiKey;
    @Value("${spider.riotgames.lol.base-url}")
    private String lolBaseUrl;
    @Value("${spider.riotgames.lol.summoner.byname.url}")
    private String lolSummonerUrl;
    @Value("${spider.riotgames.lol.summoner.byaccountid.url}")
    private String lolSummonerAccountIdUrl;
    @Value("${spider.riotgames.lol.matchlist.byaccount.url}")
    private String lolMatchListByAccountUrl;
    @Value("${spider.riotgames.lol.match.detail.url}")
    private String lolMatchDetailUrl;
    @Value("${spider.riotgames.lol.match.timelines.url}")
    private String lolMatchTimeLinesUrl;
}
