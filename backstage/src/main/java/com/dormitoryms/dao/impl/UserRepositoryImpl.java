package com.dormitoryms.dao.impl;

import com.dormitoryms.dao.IUserRepository;
import com.dormitoryms.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * 用户表操作接口实现
 *
 * @author hackyo
 * Created on 2018/3/18 16:47.
 */
@Repository
public class UserRepositoryImpl implements IUserRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void insert(User user) {
        mongoTemplate.insert(user);
    }

    @Override
    public User findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public void updateLastLoginIpByUsername(String username, String lastLoginIp) {
        Query query = new Query(Criteria.where("username").is(username));
        Update update = new Update().set("lastLoginIp", lastLoginIp);
        mongoTemplate.updateMulti(query, update, User.class);
    }

}
