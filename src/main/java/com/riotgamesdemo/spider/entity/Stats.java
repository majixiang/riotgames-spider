package com.riotgamesdemo.spider.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @date 2021/1/17 13:42
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Getter
@Setter
public class Stats {
    private Integer participantId;
    private Boolean win;
    private Integer item0;
    private Integer item1;
    private Integer item2;
    private Integer item3;
    private Integer item4;
    private Integer item5;
    private Integer item6;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer largestKillingSpree;
    private Integer largestMultiKill;
    private Integer killingSprees;
    private Integer longestTimeSpentLiving;
    private Integer doubleKills;
    private Integer tripleKills;
    private Integer quadraKills;
    private Integer pentaKills;
    private Integer unrealKills;
    private Integer totalDamageDealt;
    private Integer magicDamageDealt;
    private Integer physicalDamageDealt;
    private Integer trueDamageDealt;
    // 最大伤害
    private Integer largestCriticalStrike;
    private Integer totalDamageDealtToChampions;
    private Integer magicDamageDealtToChampions;
    private Integer physicalDamageDealtToChampions;
    private Integer trueDamageDealtToChampions;
    private Integer totalHeal;
    private Integer totalUnitsHealed;
    private Integer damageSelfMitigated;
    private Integer damageDealtToObjectives;
    private Integer damageDealtToTurrets;
    private Integer visionScore;
    private Integer timeCCingOthers;
    private Integer totalDamageTaken;
    private Integer magicalDamageTaken;
    private Integer physicalDamageTaken;
    private Integer trueDamageTaken;
    private Integer goldEarned;
    private Integer goldSpent;
    private Integer turretKills;
    private Integer inhibitorKills;
    private Integer totalMinionsKilled;
    private Integer neutralMinionsKilled;
    private Integer neutralMinionsKilledTeamJungle;
    private Integer neutralMinionsKilledEnemyJungle;
    private Integer totalTimeCrowdControlDealt;
    private Integer champLevel;
    private Integer visionWardsBoughtInGame;
    private Integer sightWardsBoughtInGame;
    private Integer wardsPlaced;
    private Integer wardsKilled;
    private Boolean firstBloodKill;
    private Boolean firstBloodAssist;
    private Boolean firstTowerKill;
    private Boolean firstTowerAssist;
    private Boolean firstInhibitorKill;
    private Boolean firstInhibitorAssist;
}
