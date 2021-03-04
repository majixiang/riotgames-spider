package com.riotgamesdemo.spider.pipeline;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.riotgamesdemo.spider.dao.MatchRepository;
import com.riotgamesdemo.spider.dao.SummonerRepository;
import com.riotgamesdemo.spider.entity.Match;
import com.riotgamesdemo.spider.entity.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @date 2021/1/17 16:02
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Component
public class RiotGamesPipeLine {
    @Autowired
    private SummonerRepository summonerRepository;
    @Autowired
    private MatchRepository matchRepository;


    private List<String> summoners = new ArrayList<>(16);
    private List<String> matches = new ArrayList<>(16);
    private BloomFilter<String> summonerBloomFilter =
            BloomFilter.create(Funnels.stringFunnel(Charset.forName("UTF-8")), 10000000, 0.00001);
    private BloomFilter<Match> matchBloomFilter =
            BloomFilter.create(new RiotGamesFunnel(), 10000000, 0.00001);

    public void addSummoner(Summoner summoner) {
        if (summonerRepository.existsById(summoner.getId())) {
            summonerRepository.updateSummoner(summoner.getId(), summoner.getAccountId(), summoner.getPuuid(), summoner.getRevisionDate(), summoner.getSummonerLevel(), summoner.getName());
        } else {
            summonerRepository.saveSummoner(summoner.getId(), summoner.getAccountId(), summoner.getPuuid(), summoner.getName(), summoner.getProfileIconId(), summoner.getRevisionDate(), summoner.getSummonerLevel(), summoner.getArea());
        }

//        if (null == summonerRepository.getSummonerByAccountId(summoner.getAccountId())) {
//            summonerRepository.saveSummoner(summoner.getId(), summoner.getAccountId(), summoner.getPuuid(), summoner.getName(), summoner.getProfileIconId(), summoner.getRevisionDate(), summoner.getSummonerLevel(), summoner.getArea());
//        }

//        if(!summonerBloomFilter.mightContain(accountId)) {
//            summonerBloomFilter.put(accountId);
//            summoners.add(accountId);
//        }
    }

    public void addSummoner(String area, String summonerId, String accountId, String name, Integer profileIcon) {
        if(null == summonerId || "".equals(summonerId)) {
            return;
        } else {
            if(!summonerRepository.existsById(summonerId)) {
                summonerRepository.addSummoner(area, summonerId, accountId, name, profileIcon);
            }
        }

    }

    public Summoner getSummoner(String area) {
        return summonerRepository.getSummoner(area);
//        Iterator<String> sIterator = summoners.listIterator();
//        if(sIterator.hasNext()) {
//            String acccountId = sIterator.next();
//            if(acccountId != null) {
//                summoners.remove(acccountId);
//            }
//            return acccountId;
//        }
//        return null;
    }

    public void addMatch(Match match) {
        System.out.println(match);
        if(null != matchRepository.queryByGameId(match.getGameId())) {
            matchRepository.addMatch(match.getGameId(),
                    match.getPlatformId(),
                    match.getChampion(),
                    match.getQueue(),
                    match.getSeason(),
                    match.getTimestamp(),
                    match.getRole(),
                    match.getLane(),
                    match.getArea());
        }

//        if (!matchBloomFilter.mightContain(match)) {
//            matchBloomFilter.put(match);
//            matches.add(match.getGameId() + "");
//        }
    }

    public String getGameId(String area) {
        Match match = matchRepository.getMatch(area.toUpperCase());
        if(match != null) {
            return match.getGameId() + "";
        }
        return null;

//        Iterator<String> sIterator = matches.listIterator();
//        if(sIterator.hasNext()) {
//            String gameId = sIterator.next();;
//            matches.remove(gameId);
//            return gameId;
//        }
//        return null;
    }
}
