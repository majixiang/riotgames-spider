package com.riotgamesdemo.spider.admin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @date 2021/1/24 18:39
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Getter
@Setter
@Table("t_admin_setting")
public class Admin {
    @Id
    private String key;
    private String value;
}
