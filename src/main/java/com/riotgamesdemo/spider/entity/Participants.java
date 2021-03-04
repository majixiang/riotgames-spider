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
public class Participants {
    private Integer participantId;
    private Integer teamId;
    private Integer championId;
    private Integer spell1Id;
    private Integer spell2Id;
}
