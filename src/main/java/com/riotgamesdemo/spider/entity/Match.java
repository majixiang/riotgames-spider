package com.riotgamesdemo.spider.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @date 2021/1/17 13:11
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Getter
@Setter
@Table("t_match")
@ToString
public class Match {
    @Id
    private Long gameId;
    private String platformId;
    private Integer champion;
    private Integer queue;
    private Integer season;
    private Long timestamp;
    private String role;
    private String lane;
    private String area;
    private Integer isRead;
}
