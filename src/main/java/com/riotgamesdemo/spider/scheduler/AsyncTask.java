package com.riotgamesdemo.spider.scheduler;

import com.riotgamesdemo.spider.dao.AdminSettingRepository;
import com.riotgamesdemo.spider.dao.MatchRepository;
import com.riotgamesdemo.spider.dao.SummonerRepository;
import com.riotgamesdemo.spider.entity.MatchList;
import com.riotgamesdemo.spider.entity.Summoner;
import com.riotgamesdemo.spider.pipeline.RiotGamesPipeLine;
import com.riotgamesdemo.spider.service.LolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * @date 2021/1/27 21:35
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Slf4j
@Service
public class AsyncTask {
    @Autowired
    private RiotGamesPipeLine pipeLine;
    @Autowired
    private LolService lolService;
    @Autowired
    private SummonerRepository summonerRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private AdminSettingRepository adminSettingRepository;


    public void summonerTask(String area) throws UnsupportedEncodingException {
        try {
            adminSettingRepository.update(Constant.SUMMONER_TASK_RUNNING, Constant.SUMMONER_TASK_RUNNING_TRUE);
            Summoner summoner = pipeLine.getSummoner(area);
            log.info("start summoner task, summoner is {}", summoner);
            while (null != summoner && null != summoner.getAccountId()) {
                if (null == summoner) {
                    summoner = lolService.getSummonerDetailsByName(area, "abc");
                } else {
                    Summoner summoner1 = lolService.getSummonerDetailsByAccountId(area, summoner.getAccountId());
                    if(summoner1.getAccountId() == summoner.getAccountId()) {
                        summoner = summoner1;
                    }
                }
                matchListTask(summoner.getAccountId(), area);

                log.info("already update the summoner is read, accountId is {}", summoner.getAccountId());
                summoner = pipeLine.getSummoner(area);
                log.info("start summoner task, summoner is {}", summoner);
            }
        } finally {
            adminSettingRepository.update(Constant.SUMMONER_TASK_RUNNING, Constant.SUMMONER_TASK_RUNNING_FALSE);
        }

    }

    private void matchListTask(String accountId, String area) {
        MatchList matchList = lolService.getMatchList(area, accountId, 0);
        try {
            if(null != matchList && matchList.getTotalGames() != null)  {
                log.info("the match list length is : {}", matchList.getTotalGames());
                for (int i = matchList.getEndIndex(); i < matchList.getTotalGames(); i += 100) {
                    MatchList matchList1 = lolService.getMatchList(area, accountId, i);
                    if(matchList1 != null) {
                        BeanUtils.copyProperties(matchList1, matchList);
                    } else {
                        break;
                    }
                    log.info("total games is {}, current index is {}", matchList.getTotalGames(), i);
                }
                summonerRepository.updateSummonerRead(accountId);
            } else {
                summonerRepository.updateSummonerReadErr(accountId);
                log.warn("the summoner : {} read error.", accountId);
            }
        } catch (Exception e) {
            summonerRepository.updateSummonerReadErr(accountId);
            log.warn("the summoner : {} read error.", accountId);
        }

    }

//    @Async
    public void matchDetailTask(String area) {
        try {
            adminSettingRepository.update(Constant.MATCH_TASK_RUNNING, Constant.MATCH_TASK_RUNNING_TRUE);
            String gameId = pipeLine.getGameId(area);
            log.info("game id is : {}", gameId);
            try {
                while (gameId != null) {
//                    if (null != matchRepository.queryByGameId(gameId)) {
                        lolService.getMatchDetail(area, gameId);
                        lolService.getGameTimeLine(area, gameId);
                        matchRepository.updateRead(gameId);
                        log.info("already update the match is read, matchId is {}", gameId);
//                    }
                    gameId = pipeLine.getGameId(area);
                    log.info("gameId is : {}", gameId);
                }
            } catch (Exception e) {
                matchRepository.updateReadErr(gameId);
            }
        } finally {
            adminSettingRepository.update(Constant.MATCH_TASK_RUNNING, Constant.MATCH_TASK_RUNNING_FALSE);
        }
    }

}
