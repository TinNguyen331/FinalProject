package com.kltn.controllers;

import com.kltn.entities.SpecialDay;
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
@RequestMapping("/specialday")
public class SpecialDayController {

    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AdminServices adminServices;

    //:GET
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<SpecialDay>> GetAllSpecialDay(){
        return new ResponseEntity<List<SpecialDay>>(customerServices.getAllSpecialDay(), HttpStatus.OK);
    }

    @RequestMapping(path = {"{id}"},method = {RequestMethod.GET})
    public ResponseEntity<SpecialDay> GetSpecialDayById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        SpecialDay result=customerServices.getSpecialDayById(objectId);
        if(result!=null)
            return new ResponseEntity<SpecialDay>(result,HttpStatus.OK);
        return new ResponseEntity<SpecialDay>(HttpStatus.NOT_FOUND);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SpecialDay> AddNewSpecialDay(@RequestBody SpecialDay model){
        SpecialDay result=adminServices.insertOrUpdateSpecialDay(model);
        if(result!=null)
            return new ResponseEntity<SpecialDay>(result,HttpStatus.OK);
        return new ResponseEntity<SpecialDay>(HttpStatus.BAD_REQUEST);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SpecialDay> EditSpecialDay(@PathVariable String id, @RequestBody SpecialDay model){
        SpecialDay result=adminServices.insertOrUpdateSpecialDay(model);
        if(result!=null)
            return new ResponseEntity<SpecialDay>(result,HttpStatus.OK);
        return new ResponseEntity<SpecialDay>(HttpStatus.NOT_FOUND);
    }

    //:DELETE
    @RequestMapping(path = {"/{id}"},method = {RequestMethod.DELETE})
    public ResponseEntity<Boolean> DeleteSpecialDay(@PathVariable String  id){
        ObjectId objectId=new ObjectId(id);
        boolean result=adminServices.deleteSpecialDay(objectId);
        if(result)
            return new ResponseEntity<Boolean>(result,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }
}
