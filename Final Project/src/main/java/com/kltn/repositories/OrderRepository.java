package com.kltn.repositories;

import com.kltn.entities.Order;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
public interface OrderRepository extends MongoRepository<Order,ObjectId> {
    @Query("{ status: 'COMPLETED'}")
    List<Order> findAllCompletedOrder();
    @Query("{ status: 'UNCOMPLETED'}")
    List<Order> findAllUncompletedOrder();

    List<Order> findByisActiveAndStatus(boolean isActive, String status, Sort sort);
    List<Order> findBymonthAndYear(int month,int year);



}
