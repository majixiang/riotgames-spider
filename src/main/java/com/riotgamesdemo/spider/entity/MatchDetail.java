package com.riotgamesdemo.spider.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @date 2021/1/17 13:26
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Getter
@Setter
@ToString
public class MatchDetail {
    private Long gameId;
    private String platformId;
    private Long gameCreation;
    private Long gameDuration;
    private Integer queueId;
    private Integer mapId;
    private Integer seasonId;
    private String gameVersion;
    private String gameMode;
    private String gameType;
    private List<Team> teams;
    private List<Participants> participants;
    private List<ParticipantIdentities> participantIdentities;
    private List<Ban> bans;
}
