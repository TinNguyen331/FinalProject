package com.kltn.repositories;

import com.kltn.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByUserName(String username);
    List<User> findByisActive(boolean isActive);
}
