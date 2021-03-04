package com.riotgamesdemo.spider.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @date 2021/1/17 13:30
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Getter
@Setter
public class Team {
    private Integer teamId;
    private Win win;
    private Boolean firstBlood;
    private Boolean firstTower;
    private Boolean firstInhibitor;
    private Boolean firstBaron;
    private Boolean firstDragon;
    private Boolean firstRiftHerald;
    private Integer towerKills;
    private Integer inhibitorKills;
    private Integer baronKills;
    private Integer dragonKills;
    private Integer vilemawKills;
    private Integer riftHeraldKills;
    private Integer dominionVictoryScore;
}
