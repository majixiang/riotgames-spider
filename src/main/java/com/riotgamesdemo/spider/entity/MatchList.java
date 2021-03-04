package com.riotgamesdemo.spider.entity;

import lombok.Data;

import java.util.List;

/**
 * @date 2021/1/17 14:48
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Data
public class MatchList {
    private List<Match> matches;
    private Integer startIndex;
    private Integer endIndex;
    private Integer totalGames;
}
