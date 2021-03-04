package com.riotgamesdemo.spider.dao;

import com.riotgamesdemo.spider.entity.Summoner;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @date 2021/1/24 17:54
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Repository
public interface SummonerRepository extends CrudRepository<Summoner, String>  {

    @Query("select * from t_summoners where is_read = 0 and area =:area limit 1")
    Summoner getSummoner(String area);

    @Query("select * from t_summoners where account_id = :accountId ")
    Summoner getSummonerByAccountId(String accountId);

    @Modifying
    @Query("update t_summoners set is_read = 1 where account_id = :accountId")
    void updateSummonerRead(String accountId);

    @Modifying
    @Query("update t_summoners set is_read = 2 where account_id = :accountId")
    void updateSummonerReadErr(String accountId);

    @Modifying
    @Query("update t_summoners set account_id = :accountId, puuid =:puuid, summoner_level =:summonerLevel, revision_date =:revisionDate, name = :name where id = :summonerId")
    void updateSummoner(String summonerId, String accountId, String puuid, Long revisionDate, Integer summonerLevel, String name);


    @Query("insert into t_summoners(id, account_id, name, profile_icon_id, is_read, area) values(:summonerId, :accountId, :name, :profileIcon, 0, :area)")
    @Modifying
    void addSummoner(String area, String summonerId, String accountId, String name, Integer profileIcon);

    @Modifying
    @Query("insert into t_summoners values(:id, :accountId," +
            ":puuid,:name,:profileIconId,:revisionDate,:summonerLevel,0,:area)")
    void saveSummoner(String id, String accountId, String puuid, String name , Integer profileIconId, Long revisionDate, Integer summonerLevel, String area);
}
