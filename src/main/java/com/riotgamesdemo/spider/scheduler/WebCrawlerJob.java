package com.riotgamesdemo.spider.scheduler;

import com.riotgamesdemo.spider.client.RiotGamesClient;
import com.riotgamesdemo.spider.config.Area;
import com.riotgamesdemo.spider.dao.AdminSettingRepository;
import com.riotgamesdemo.spider.dao.MatchRepository;
import com.riotgamesdemo.spider.dao.SummonerRepository;
import com.riotgamesdemo.spider.pipeline.RiotGamesPipeLine;
import com.riotgamesdemo.spider.service.LolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @date 2021/1/16 20:13
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2020 www.jixiang.ma all right reserved.
 **/
@Slf4j
@Component
public class WebCrawlerJob {
    @Autowired
    private RiotGamesClient client;
    @Autowired
    private AdminSettingRepository adminSettingRepository;
    @Autowired
    private AsyncTask asyncTask;

    private static Lock lock = new ReentrantLock();

    @Scheduled(cron = "0 0/1 * * * ?")
    public void crawlerJob() {
        if (lock.tryLock()) {
            try {
                doExecute();
            } catch (UnsupportedEncodingException e) {
                log.error("the url encoding is error. ", e);
            } finally {
                lock.unlock();
            }
        } else {
            log.warn("the previous job has not finished, so this job is canceled");
        }
    }

    private void doExecute() throws UnsupportedEncodingException {
        log.info("start spider");
        Area value = Area.br1;

        log.info("the area is : {}", value.name());

//        String summonerTaskState = adminSettingRepository.findById(Constant.SUMMONER_TASK_RUNNING).get().getValue();

//        if(Constant.SUMMONER_TASK_RUNNING_FALSE.equals(summonerTaskState)) {
//            log.info("start summoner task");
//            asyncTask.summonerTask(value.name());
//        } else {
//            log.info("the summoner task is running, this task stop.");
//        }
        String matchTaskState = adminSettingRepository.findById(Constant.MATCH_TASK_RUNNING).get().getValue();
        if(Constant.MATCH_TASK_RUNNING_FALSE.equals(matchTaskState)) {
            log.info("start match task");
            asyncTask.matchDetailTask(value.name());
        } else {
            log.info("the match task is running, this task stop.");
        }
    }
}
