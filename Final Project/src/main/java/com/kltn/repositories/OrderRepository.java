package com.kltn.repositories;

import com.kltn.entities.Order;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by TinNguyen on 5/12/17.
 */
@Repository
@RepositoryRestResource(exported = false)
public interface OrderRepository extends MongoRepository<Order,ObjectId> {
    @Query("{ status: 'COMPLETED'}")
    List<Order> findAllCompletedOrder();
    @Query("{ status: 'UNCOMPLETED'}")
    List<Order> findAllUncompletedOrder();

    List<Order> findByisActiveAndStatus(boolean isActive, String status, Sort sort);
    List<Order> findByisActiveAndMonthAndYear(boolean isActive,int month,int year);
    List<Order> findByisActiveAndYearAndStatus(boolean isActive,int year,String status,Sort sort);

    List<Order> findByisActiveAndDateOrderBetween(boolean isActive, Date start,Date end);
    List<Order> findByisActiveAndDateDeliveryBetweenAndStatus(boolean isActive, Date start,Date end,String status);
    List<Order> findByisActiveAndMonthAndYearAndStatus(boolean isActive,int month,int year,String status);

    Order findByDayAndMonthAndStatus(int day,int month,String status,Sort sort);

}
