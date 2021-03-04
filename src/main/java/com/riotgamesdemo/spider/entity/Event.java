package com.riotgamesdemo.spider.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @date 2021/1/17 15:00
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Getter
@Setter
@ToString
public class Event {
    private String type;
    private Long timestamp;
    private Integer participantId;
    private Integer skillSlot;
    private String levelUpType;
}
