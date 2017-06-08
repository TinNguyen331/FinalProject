package com.kltn.repositories;

import com.kltn.entities.Notify;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
public interface NotifyRepository extends MongoRepository<Notify,ObjectId> {
    List<Notify> findBystatus(boolean status, Sort sort);

}
