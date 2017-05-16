package com.kltn.controllers;

import com.kltn.entities.PriceByDay;
import com.kltn.services.AdminServices;
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
@RequestMapping("/pricebyday")
public class PriceByDayController {

    @Autowired
    private AdminServices adminServices;

    //:GET
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<PriceByDay>> GetAllPriceByDay(){
        return new ResponseEntity<List<PriceByDay>>(adminServices.getAllPriceByDay(), HttpStatus.OK);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PriceByDay> AddNewPriceByDay(@RequestBody PriceByDay model){
        PriceByDay result=adminServices.insertOrUpdatePriceByDay(model);
        if(result!=null)
            return new ResponseEntity<PriceByDay>(result,HttpStatus.OK);
        return new ResponseEntity<PriceByDay>(HttpStatus.BAD_REQUEST);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PriceByDay> EditPriceByDay(@PathVariable String id, @RequestBody PriceByDay model){
        PriceByDay result=adminServices.insertOrUpdatePriceByDay(model);
        if(result!=null)
            return new ResponseEntity<PriceByDay>(result,HttpStatus.OK);
        return new ResponseEntity<PriceByDay>(HttpStatus.NOT_FOUND);
    }


}
