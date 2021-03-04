package com.riotgamesdemo.spider.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @date 2021/1/16 20:22
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Getter
@Setter
@ToString
@Table("t_summoners")
public class Summoner {
    @Id
    private String id;
    @Column("account_id")
    private String accountId;
    private String puuid;
    private String name;
    @Column("profile_icon_id")
    private Integer profileIconId;
    @Column("revision_date")
    private Long revisionDate;
    @Column("summoner_level")
    private Integer summonerLevel;
    @Column("is_read")
    private Integer isRead;
    private String area;
}
