package com.riotgamesdemo.spider.dao;

import com.riotgamesdemo.spider.entity.Match;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @date 2021/1/24 18:04
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    @Modifying
    @Query("insert into t_match values(:gameId, :platformId, :champion, :queue, :season, :timestamp, :role, :lane, :area, 0)")
    void addMatch(Long gameId, String platformId, Integer champion, Integer queue, Integer season, Long timestamp, String role, String lane, String area);

    @Modifying
    @Query("update t_match set is_read = 1 where game_id =:gameId")
    void updateRead(String gameId);

    @Modifying
    @Query("update t_match set is_read = 2 where game_id =:gameId")
    void updateReadErr(String gameId);

    @Query("select 1 from t_match where game_id =:gameId")
    Integer queryByGameId(Long gameId);

    @Query("select * from t_match where is_read = 0 and platform_id = :area limit 1")
    Match getMatch(String area);
}
