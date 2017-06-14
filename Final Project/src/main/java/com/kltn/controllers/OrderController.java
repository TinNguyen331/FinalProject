package com.kltn.controllers;

import com.kltn.entities.Order;
import com.kltn.services.AdminServices;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TinNguyen on 5/17/17.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AdminServices adminServices;

    //:GET
    @RequestMapping(path = {"/count"},method = {RequestMethod.GET})
    public ResponseEntity<Long> GetCounterOrder(){
        return new ResponseEntity<Long>(adminServices.countOrder(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<Order>> GetAllOrder(){
        return new ResponseEntity<List<Order>>(adminServices.getAllOrder(), HttpStatus.OK);
    }

    @RequestMapping(path = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<Order> GetOrderById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        Order result=customerServices.getOrderById(objectId);
        if(result!=null)
            return new ResponseEntity<Order>(result,HttpStatus.OK);
        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }

    //1 Là Completed, 0 là Uncompleted
    @RequestMapping(path = {"/sort/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<List<Order>> GetOrderBySort(@PathVariable int id){
        boolean bol=false;
        if(id==1)
            bol=true;
        return new ResponseEntity<List<Order>>(adminServices.getAllOrderCompletedOrUnCompleted(bol),HttpStatus.OK);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Order> EditOrder(@PathVariable String id, @RequestBody Order model){
        Order result=adminServices.insertOrUpdateOrder(model);
        if(result!=null)
            return new ResponseEntity<Order>(result,HttpStatus.OK);
        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }

}
