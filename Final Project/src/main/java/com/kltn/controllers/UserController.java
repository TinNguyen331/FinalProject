package com.kltn.controllers;

import com.kltn.bo.OrderUser;
import com.kltn.entities.User;
import com.kltn.services.AdminServices;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TinNguyen on 5/16/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AdminServices adminServices;

    //:GET
    @RequestMapping(path = {"/count"},method = {RequestMethod.GET})
    public ResponseEntity<Long> GetCounterUser(){
        return new ResponseEntity<Long>(adminServices.countUser(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<User>> GetAllUsers(){
        return new ResponseEntity<List<User>>(customerServices.getAllUser(), HttpStatus.OK);
    }

    @RequestMapping(path = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<User> GetUserById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        return new ResponseEntity<User>(customerServices.getUserById(objectId),HttpStatus.OK);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> AddNewUser(@RequestBody User model){
        User result=adminServices.insertOrUpdateUser(model);
        if(result!=null)
            return new ResponseEntity<User>(result,HttpStatus.OK);
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    //Note need check
    @RequestMapping(path = {"/add-order"},method = {RequestMethod.POST},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> AddOrderUser(@RequestBody OrderUser model){
        return new ResponseEntity<User>(customerServices.updateOrderListOfUser(model),HttpStatus.OK);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> EditUser(@PathVariable String id, @RequestBody User model){
        User result=adminServices.insertOrUpdateUser(model);
        if(result!=null)
            return new ResponseEntity<User>(result,HttpStatus.OK);
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    //:DELETE
    @RequestMapping(path = {"/{id}"},method = {RequestMethod.DELETE})
    public ResponseEntity<Boolean> DeleteUser(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        boolean result=adminServices.deleteUser(objectId);
        if(result)
            return new ResponseEntity<Boolean>(result,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }
}
