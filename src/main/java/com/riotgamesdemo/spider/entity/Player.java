package com.riotgamesdemo.spider.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @date 2021/1/17 15:56
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Getter
@Setter
@ToString
public class Player {
    private String platformId;
    private String accountId;
    private String summonerName;
    private String summonerId;
    private String currentAccountId;
    private String currentPlatformId;
    private String matchHistoryUri;
    private Integer profileIcon;
}
