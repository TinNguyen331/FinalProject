package com.kltn.repositories;

import com.kltn.entities.Meaning;
import com.sun.org.apache.xpath.internal.operations.String;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
public interface MeaningRepository extends MongoRepository<Meaning,ObjectId> {
}
