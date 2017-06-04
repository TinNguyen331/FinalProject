package com.kltn.repositories;

import com.kltn.entities.Authority;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by TinNguyen on 6/4/17.
 */
@Repository
public interface AuthorityRepository extends MongoRepository<Authority,ObjectId> {
}
