package com.kltn.repositories;

import com.kltn.entities.SpecialDay;
import com.sun.org.apache.xpath.internal.operations.String;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
public interface SpecialDayRepository extends MongoRepository<SpecialDay,ObjectId> {
    List<SpecialDay> findByisActive(boolean isActive);
}
