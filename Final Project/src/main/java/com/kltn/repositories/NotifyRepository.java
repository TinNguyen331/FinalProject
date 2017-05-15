package com.kltn.repositories;

import com.kltn.entities.Notify;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
public interface NotifyRepository extends MongoRepository<Notify,ObjectId> {
    Notify getNotifyByStatus(String status);
}
