package com.kltn.repositories;

import com.kltn.entities.Product;
import com.sun.org.apache.xpath.internal.operations.String;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product,ObjectId> {

    @Query(value = "{ 'categoryId.$id : ?0'}")
    List<Product> findAllProductByCateId(ObjectId objectId);
}
