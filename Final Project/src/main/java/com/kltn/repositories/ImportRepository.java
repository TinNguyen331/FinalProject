package com.kltn.repositories;

import com.kltn.entities.Import;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TinNguyen on 6/9/17.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface ImportRepository extends MongoRepository<Import,ObjectId> {
    List<Import> findByYear(int year, Sort sort);
}
