package com.riotgamesdemo.spider.dao;

import com.riotgamesdemo.spider.admin.Admin;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @date 2021/1/24 18:40
 * @auth jixiang.ma@transwarp.io
 * @copyright copyright © 2021 www.jixiang.ma all right reserved.
 **/
@Repository
public interface AdminSettingRepository extends CrudRepository<Admin, String> {
    @Modifying
    @Query("update t_admin_setting set value = :value where `key` = :key")
    void update(String key, String value);

    @Query("insert into t_admin_setting values(:key, :value)")
    void add(String key, String value);
}
