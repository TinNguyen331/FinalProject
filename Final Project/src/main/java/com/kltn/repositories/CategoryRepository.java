package com.kltn.repositories;

import com.kltn.entities.Category;
import com.sun.org.apache.xpath.internal.operations.String;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by TinNguyen on 5/4/17.
 */
@Repository
public interface CategoryRepository extends MongoRepository<Category,ObjectId>{
}
