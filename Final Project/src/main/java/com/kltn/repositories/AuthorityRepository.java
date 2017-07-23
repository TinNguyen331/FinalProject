package com.kltn.repositories;

import com.kltn.entities.Authority;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by TinNguyen on 6/4/17.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface AuthorityRepository extends MongoRepository<Authority,ObjectId> {
}
