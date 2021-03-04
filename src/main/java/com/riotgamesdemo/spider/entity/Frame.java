package com.riotgamesdemo.spider.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @date 2021/1/17 14:59
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Getter
@Setter
@ToString
public class Frame {
    private List<ParticipantFrame> participantFrames;
    private Long timestamp;
    private List<Event> events;
}
