package com.riotgamesdemo.spider.pipeline;

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;
import com.riotgamesdemo.spider.entity.Match;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * @date 2021/1/24 17:38
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
public class RiotGamesFunnel implements Funnel<Match>, Serializable {
    @Override
    public void funnel(Match match, PrimitiveSink primitiveSink) {
        primitiveSink.putString(match.getArea() + match.getGameId(), Charset.forName("UTF-8"));
    }

}
