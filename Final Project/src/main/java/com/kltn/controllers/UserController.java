package com.kltn.controllers;

import com.kltn.bo.OrderUser;
import com.kltn.bo.UserDTO;
import com.kltn.entities.User;
import com.kltn.services.AdminServices;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private ModelMapper modelMapper;

    //:GET
    @RequestMapping(path = {"/count"},method = {RequestMethod.GET})
    public ResponseEntity<Long> GetCounterUser(){
        return new ResponseEntity<Long>(adminServices.countUser(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<User>> GetAllUsers(){
        return new ResponseEntity<List<User>>(customerServices.getAllUser(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<User> GetUserById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        return new ResponseEntity<User>(customerServices.getUserById(objectId),HttpStatus.OK);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> AddNewUser(@RequestBody UserDTO model){
        User user=convertFromDTO(model);
        User result=adminServices.insertOrUpdateUser(user);
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
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = {"/{id}"},method = {RequestMethod.DELETE})
    public ResponseEntity<Boolean> DeleteUser(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        boolean result=adminServices.deleteUser(objectId);
        if(result)
            return new ResponseEntity<Boolean>(result,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path={"/active/{id}"}, method = {RequestMethod.DELETE})
    public ResponseEntity<Boolean> ActiveUser(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        boolean result=adminServices.activeUser(objectId);
        if(result)
            return new ResponseEntity<Boolean>(result,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }

    private User convertFromDTO(UserDTO dto){
        User user=new User();
        if(!dto.getId().isEmpty()){
            user.setId(new ObjectId(dto.getId()));
        }
        user.setUserName(dto.getUserName());
        user.setPassWord(dto.getPassWord());
        user.setFullName(dto.getFullName());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setActiveIndexAddress(dto.getActiveIndexAddress());
        return user;
    }
}
