package com.kltn.controllers;

import com.kltn.entities.Event;
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
@RequestMapping("/event")
public class EventController {

    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AdminServices adminServices;

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<Event>> GetAllEvent(){
        return new ResponseEntity<List<Event>>(customerServices.getAllEvent(), HttpStatus.OK);
    }

    @RequestMapping(path = {"/{id"},method = {RequestMethod.GET})
    public ResponseEntity<Event> GetEventById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        Event result=customerServices.getEventById(objectId);
        if(result!=null)
            return new ResponseEntity<Event>(result,HttpStatus.OK);
        return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Event> AddNewEvent(@RequestBody Event model){
        Event result=adminServices.insertOrUpdateEvent(model);
        if(result!=null)
            return new ResponseEntity<Event>(result,HttpStatus.OK);
        return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Event> EditEvent(@PathVariable String id, @RequestBody Event model){
        Event result=adminServices.insertOrUpdateEvent(model);
        if(result!=null)
            return new ResponseEntity<Event>(result,HttpStatus.OK);
        return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
    }

    //:DELETE
    @RequestMapping(path = {"/{id}"},method = {RequestMethod.DELETE})
    public ResponseEntity<Boolean> DeleteEvent(@PathVariable String  id){
        ObjectId objectId=new ObjectId(id);
        boolean result=adminServices.deleteEvent(objectId);
        if(result)
            return new ResponseEntity<Boolean>(result,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }
}
