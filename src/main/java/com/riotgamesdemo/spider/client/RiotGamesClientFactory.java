package com.riotgamesdemo.spider.client;

import com.riotgamesdemo.spider.config.RiotGamesConfig;
import com.riotgamesdemo.spider.dao.AdminSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @date 2021/1/28 22:16
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Component
public class RiotGamesClientFactory {
    @Autowired
    private RiotGamesConfig riotGamesConfig;

    @Autowired
    private AdminSettingRepository adminSettingRepository;


    @Bean
    public RiotGamesClient getClient() {
        RiotGamesClient client = new RiotGamesClient(riotGamesConfig.getLolBaseUrl(),
                adminSettingRepository.findById("apikey").get().getValue());
        client.init();
        return client;
    }
}
