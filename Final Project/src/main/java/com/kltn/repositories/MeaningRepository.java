package com.kltn.repositories;

import com.kltn.entities.Meaning;
import com.sun.org.apache.xpath.internal.operations.String;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface MeaningRepository extends MongoRepository<Meaning,ObjectId> {
}
