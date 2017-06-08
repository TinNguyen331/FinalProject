package com.kltn.repositories;

import com.kltn.entities.Import;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by TinNguyen on 6/9/17.
 */
public interface ImportRepository extends MongoRepository<Import,ObjectId> {
}
