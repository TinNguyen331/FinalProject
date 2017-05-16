package com.kltn.controllers;

import com.kltn.entities.Meaning;
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
@RequestMapping("/meaning")
public class MeaningController {

    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AdminServices adminServices;

    //:GET
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<Meaning>> GetAllMeaning(){
        return new ResponseEntity<List<Meaning>>(customerServices.getAllMeaning(), HttpStatus.OK);
    }

    @RequestMapping(path = {"/ramdom/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<List<Meaning>> GetRandomMeaning(@PathVariable int id){
        return new ResponseEntity<List<Meaning>>(customerServices.getRandomMeaning(id),HttpStatus.OK);
    }

    @RequestMapping(path = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<Meaning> GetMeaningById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        Meaning result=customerServices.getMeaningById(objectId);
        if(result!=null)
            return new ResponseEntity<Meaning>(result,HttpStatus.OK);
        return new ResponseEntity<Meaning>(HttpStatus.NOT_FOUND);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Meaning> AddNewMeaning(@RequestBody Meaning model){
        Meaning result=adminServices.insertOrUpdateMeaning(model);
        if(result!=null)
            return new ResponseEntity<Meaning>(result,HttpStatus.OK);
        return new ResponseEntity<Meaning>(HttpStatus.BAD_REQUEST);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Meaning> EditMeaning(@PathVariable String id, @RequestBody Meaning model){
        Meaning result=adminServices.insertOrUpdateMeaning(model);
        if(result!=null)
            return new ResponseEntity<Meaning>(result,HttpStatus.OK);
        return new ResponseEntity<Meaning>(HttpStatus.NOT_FOUND);
    }
}
