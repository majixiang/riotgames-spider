package com.riotgamesdemo.spider.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @date 2021/1/17 14:27
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Setter
@Getter
@ToString
public class ParticipantFrame {
    private Integer participantId;
    private Integer currentGold;
    private Integer totalGold;
    private Integer level;
    private Integer xp;
    private Integer minionsKilled;
    private Integer jungleMinionsKilled;
    private Integer dominionScore;
    private Integer teamScore;
}
