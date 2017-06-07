package com.kltn.Util.Impl;

import com.kltn.Util.UserUtil;
import com.kltn.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TinNguyen on 6/7/17.
 */
@Repository("UserUtil")
public class UserUtilImpl implements UserUtil {

    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public List<User> findAllActiveUserExpectAdmin() {
        Query query=new Query();
        query.addCriteria(Criteria.where("authorities.name").is("ROLE_USER").and("isActive").is(true));
        return mongoTemplate.find(query,User.class);
    }
}
