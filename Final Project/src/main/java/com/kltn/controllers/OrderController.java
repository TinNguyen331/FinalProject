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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by TinNguyen on 5/17/17.
 */
@PreAuthorize("hasRole('ADMIN')")
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


    @RequestMapping(path = "/new",method = {RequestMethod.GET})
    public ResponseEntity<List<Order>> GetAllNewOrder(){
        return new ResponseEntity<List<Order>>(adminServices.getAllNewOrder(), HttpStatus.OK);
    }


    @RequestMapping(path = "/sending",method = {RequestMethod.GET})
    public ResponseEntity<List<Order>> GetAllSendingOrder(){
        return new ResponseEntity<List<Order>>(adminServices.getAllSendingOrder(), HttpStatus.OK);
    }

    @RequestMapping(path = "/delivery",method = {RequestMethod.GET})
    public ResponseEntity<List<Order>> GetAllCompletedOrder(){
        return new ResponseEntity<List<Order>>(adminServices.getAllCompletedOrder(), HttpStatus.OK);
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
    @RequestMapping(path={"/package/{id}"},method = {RequestMethod.GET} )
    public ResponseEntity<Order> PackageOrder(@PathVariable String id){
        Order model=customerServices.getOrderById(new ObjectId(id));
        Date toDay=new Date();
        model.setDatePackage(toDay);
        model.setStatus("SENDING");
        Order result=adminServices.updateOrder(model);
        if(result!=null)
            return new ResponseEntity<Order>(result,HttpStatus.OK);
        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path={"/delivery/{id}"},method = {RequestMethod.GET} )
    public ResponseEntity<Order> DeliveryOrder(@PathVariable String id){
        Order model=customerServices.getOrderById(new ObjectId(id));
        Date toDay=new Date();
        model.setDateDelivery(toDay);
        model.setStatus("DELIVERY");
        Order result=adminServices.updateOrder(model);
        if(result!=null)
            return new ResponseEntity<Order>(result,HttpStatus.OK);
        return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
    }

    //:DELETE

    @RequestMapping(path = {"/{id}"},method = {RequestMethod.DELETE})
    public ResponseEntity<Boolean> DeleteOrder(@PathVariable String  id){
        ObjectId objectId=new ObjectId(id);
        boolean result=adminServices.deleteOrder(objectId);
        if(result)
            return new ResponseEntity<Boolean>(result,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }
}
