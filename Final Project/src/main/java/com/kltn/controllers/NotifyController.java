package com.kltn.controllers;

import com.kltn.entities.Notify;
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
 * Created by TinNguyen on 5/17/17.
 */
@RestController
@RequestMapping("/notify")
public class NotifyController {

    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AdminServices adminServices;

    //:GET
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<Notify>> GetAllNotify(){
        return new ResponseEntity<List<Notify>>(customerServices.getNotifyByStatus("Yes"), HttpStatus.OK);
    }

    @RequestMapping(path = {"/{id"},method = {RequestMethod.GET})
    public ResponseEntity<Notify> GetNotifytById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        Notify result=customerServices.getNotifyById(objectId);
        if(result!=null)
            return new ResponseEntity<Notify>(result,HttpStatus.OK);
        return new ResponseEntity<Notify>(HttpStatus.NOT_FOUND);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Notify> AddNewNotify(@RequestBody Notify model){
        Notify result=adminServices.inserOrUpdateNotify(model);
        if(result!=null)
            return new ResponseEntity<Notify>(result,HttpStatus.OK);
        return new ResponseEntity<Notify>(HttpStatus.BAD_REQUEST);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Notify> EditNotify(@PathVariable String id, @RequestBody Notify model){
        Notify result=adminServices.inserOrUpdateNotify(model);
        if(result!=null)
            return new ResponseEntity<Notify>(result,HttpStatus.OK);
        return new ResponseEntity<Notify>(HttpStatus.NOT_FOUND);
    }

}
