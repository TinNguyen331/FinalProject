package com.kltn.controllers;

import com.kltn.bo.ChangePasswordDTO;
import com.kltn.bo.OrderDTO;
import com.kltn.bo.OrderDetailDTO;
import com.kltn.bo.UserDTO;
import com.kltn.entities.*;
import com.kltn.services.AdminServices;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
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

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(path = {"/info"},method = {RequestMethod.GET})
    public ResponseEntity<User> GetInfoUser(Principal principal){
        User user=adminServices.getUserByName(principal.getName());
        if(user!=null)
            return new ResponseEntity<User>(user,HttpStatus.OK);
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = {"/history"},method = {RequestMethod.GET})
    public ResponseEntity<List<Order>> GetHistoryOrder(Principal principal){
        User user=adminServices.getUserByName(principal.getName());
        if(user!=null)
            return new ResponseEntity<List<Order>>(user.getOrderList(),HttpStatus.OK);
        return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> AddNewUser(@RequestBody UserDTO model){
        //Not check UserAlready in DB
        User user=convertFromDTO(model);
        User result=adminServices.insertOrUpdateUser(user);
        if(result!=null)
            return new ResponseEntity<User>(result,HttpStatus.OK);
        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(path = {"/changepassword"},method = {RequestMethod.POST},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> ChangePassWord(@RequestBody ChangePasswordDTO model, Principal principal){
        User user=adminServices.getUserByName(principal.getName());
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        boolean matches=bCryptPasswordEncoder.matches(model.getOldPass(),user.getPassWord());
        if(!matches || !model.getNewPass().equals(model.getVerifyNewPass()))
            return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
        String hashPassword=bCryptPasswordEncoder.encode(model.getNewPass());
        user.setPassWord(hashPassword);
        User result=adminServices.insertOrUpdateUser(user);
        if(result!=null)
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
    }


    //Note need check
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(path = {"/add-order"},method = {RequestMethod.POST},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> AddOrderUser(@RequestBody OrderDTO model,Principal principal){
        User user=adminServices.getUserByName(principal.getName());
        Order order=convertFromDTO(model);
        order.setPhone(user.getPhone());
        Order orderResult=adminServices.insertOrder(order);
        if(orderResult!=null) {
            user.getOrderList().add(orderResult);
            User result = adminServices.insertOrUpdateUser(user);
            if (result != null)
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> EditUser(@PathVariable String id, @RequestBody UserDTO model,Principal principal){
        User user=updateUserDTO(model,principal);
        User result=adminServices.insertOrUpdateUser(user);
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
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        user.setPassWord(bCryptPasswordEncoder.encode(dto.getPassWord())); //Hash password using BCrypt
        user.setFullName(dto.getFullName());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setActiveIndexAddress(dto.getActiveIndexAddress());

        //Add birthday of user to special day
        SpecialDayOfUser specialDayOfUser=new SpecialDayOfUser();
        specialDayOfUser.setDate(dto.getDateOfBirth());
        specialDayOfUser.setDescription("Happy birthday :"+dto.getFullName());
        List<SpecialDayOfUser> lsDayOfUsers=new ArrayList<>();
        lsDayOfUsers.add(specialDayOfUser);
        user.setSpecialDayOfUsers(lsDayOfUsers);
        return user;
    }

    private User updateUserDTO(UserDTO userDTO,Principal principal){
        User user=adminServices.getUserByName(principal.getName());

        user.setFullName(userDTO.getFullName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setActiveIndexAddress(userDTO.getActiveIndexAddress());
        return user;
    }

    private Order convertFromDTO(OrderDTO orderDTO){
        Order order=new Order(orderDTO.getAddressDelivery());
        order.setTotalCost(orderDTO.getTotalCost());
        order.setReceiver(orderDTO.getReceiver());
        List<Detail> details=new ArrayList<>();
        for (OrderDetailDTO detailDTO:orderDTO.getDetails()
             ) {
            Product pro=customerServices.getProductById(new ObjectId(detailDTO.getProductId()));
            Detail detail=new Detail(pro,detailDTO.getQuantity(),detailDTO.getPrice());
            details.add(detail);
        }
        order.setDetails(details);
        return  order;
    }
}
