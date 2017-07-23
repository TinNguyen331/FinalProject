package com.kltn.repositories;

import com.kltn.entities.Event;
import com.sun.org.apache.xpath.internal.operations.String;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface EventRepository extends MongoRepository<Event,ObjectId> {
    List<Event> findByfromDateGreaterThan(Date fromDate, Sort sort);
    Event findByFromDateLessThanEqualAndToDateGreaterThanEqual(Date fromDay,Date toDay);
}
