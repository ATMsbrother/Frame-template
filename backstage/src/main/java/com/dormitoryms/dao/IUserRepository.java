package com.dormitoryms.dao;


import com.dormitoryms.domain.User;

/**
 * 用户表操作接口
 *
 * @author hackyo
 * Created on 2018/3/18 11:53.
 */
public interface IUserRepository {

    /**
     * 添加用户
     *
     * @param user 用户
     */
    void insert(User user);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 通过用户名更新最后登录IP
     *
     * @param username    用户名
     * @param lastLoginIp 最后登录IP
     */
    void updateLastLoginIpByUsername(String username, String lastLoginIp);

}
