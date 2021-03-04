package com.riotgamesdemo.spider.service;

import com.alibaba.fastjson.JSONObject;
import com.riotgamesdemo.spider.client.RiotGamesClient;
import com.riotgamesdemo.spider.config.RiotGamesConfig;
import com.riotgamesdemo.spider.dao.MatchRepository;
import com.riotgamesdemo.spider.dao.SummonerRepository;
import com.riotgamesdemo.spider.entity.*;
import com.riotgamesdemo.spider.pipeline.RiotGamesPipeLine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Optional;

/**
 * @date 2021/1/16 20:21
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class LolService {
    private RiotGamesClient riotGamesClient;
    private RiotGamesConfig riotGamesConfig;
    private RiotGamesPipeLine riotGamesPipeLine;
    private SummonerRepository summonerRepository;
    private MatchRepository matchRepository;

    public Summoner getSummonerDetailsByAccountId(String area, String accountId) {
        return getSummonerDetails(area, String.format(riotGamesConfig.getLolSummonerAccountIdUrl(), area, accountId));
    }

    public Summoner getSummonerDetailsByName(String area, String summonerName) throws UnsupportedEncodingException {
        return getSummonerDetails(area, String.format(riotGamesConfig.getLolSummonerUrl(), area, URLEncoder.encode(summonerName, "utf-8")));
    }

    public Summoner getSummonerDetails(String area, String url) {
        HttpGet httpGet = new HttpGet(url);
        Summoner summoner = null;
        try {
            HttpResponse response = riotGamesClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 403) {
                log.error("the token is unexpired.");
            };
            String result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            summoner = JSONObject.parseObject(result, Summoner.class);
        } catch (IOException e) {
            log.error("when get summoner details, it has error taken place!", e);
        }
        if(summoner != null) {
            summoner.setArea(area);
            if(null != summoner.getAccountId() && null != summoner.getId()) {
                riotGamesPipeLine.addSummoner(summoner);
            }
        }
        return summoner;
    }

    public MatchList getMatchList(String area, String accountId, Integer beginIndex) {
        HttpGet httpGet = new HttpGet(String.format(riotGamesConfig.getLolMatchListByAccountUrl(), area, accountId, beginIndex));
        MatchList matches = null;
        try {
            HttpResponse response = riotGamesClient.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            matches = JSONObject.parseObject(result).toJavaObject(MatchList.class);
        } catch (IOException e) {
            log.error("when get match list, it has error taken place, accountId is {}", accountId, e);
        }
        if(null != matches && matches.getMatches() != null) {
            matches.getMatches().forEach(match -> riotGamesPipeLine.addMatch(match));
        }

        return matches;
    }

    public MatchDetail getMatchDetail(String area, String matchId) {
        HttpGet httpGet = new HttpGet(String.format(riotGamesConfig.getLolMatchDetailUrl(), area, matchId));
        MatchDetail matchDetail = null;
        String result = null;
        try {
            HttpResponse response = riotGamesClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            matchDetail = JSONObject.parseObject(result, MatchDetail.class);
        } catch (IOException e) {
            log.error("when get match detail, something error has taken place, matchId is {}", matchId, e);
        }
        if(null != matchDetail) {
            if(null != matchDetail.getParticipantIdentities()) {
                matchDetail.getParticipantIdentities().forEach(participant -> {
                    if(participant != null) {
                        Player player = participant.getPlayer();
                        if(null != player) {
                            log.info(matchId + "\t" + player.getSummonerId());
                            riotGamesPipeLine.addSummoner(area, player.getSummonerId(), player.getAccountId(), player.getSummonerName(), player.getProfileIcon());
                        }
                    }
                });
            }
            saveJsonFile(result, "E:\\riotgames\\data\\"+ area + "_" + matchId+"_detail.json");
        }

        return matchDetail;
    }

    public TimeLine getGameTimeLine(String area, String matchId) throws UnsupportedEncodingException {
        HttpGet httpGet = new HttpGet(String.format(riotGamesConfig.getLolMatchTimeLinesUrl(), area, matchId));
        TimeLine timeLine = null;
        String result = null;
        try {
            HttpResponse response = riotGamesClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity(), Charset.forName("UTF-8"));
            timeLine = JSONObject.parseObject(result, TimeLine.class);
        } catch (IOException e) {
            log.error("when get match details, something error taken place, matchId is {}", matchId, e);
        }
        saveJsonFile(result, "E:\\riotgames\\data\\"+ area +"_" + matchId + "_timeline.json");
        return timeLine;
    }

    private void saveJsonFile(String data, String path) {
        if(data != null ) {
            File file = new File(path);
            if(!file.exists()) {
                try {
                    if(file.createNewFile()) {
                        FileUtils.write(file, data, false);
                    }
                } catch (IOException e) {
                    log.error("write match info to file failed.", e);
                }
            }
            log.info("success save file : {}", path);
        }
    }

}
