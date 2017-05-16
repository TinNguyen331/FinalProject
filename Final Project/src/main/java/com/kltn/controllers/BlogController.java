package com.kltn.controllers;

import com.kltn.entities.Blog;
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
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AdminServices adminServices;

    //:GET
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<Blog>> GetAllBlog(){
        return new ResponseEntity<List<Blog>>(customerServices.getAllBlog(), HttpStatus.OK);
    }

    @RequestMapping(path = {"/ramdom/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<List<Blog>> GetRandomBlog(@PathVariable int id){
        return new ResponseEntity<List<Blog>>(customerServices.getRandomnBlog(id),HttpStatus.OK);
    }

    @RequestMapping(path = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<Blog> GetBlogById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        Blog result=customerServices.getBlogById(objectId);
        if(result!=null)
            return new ResponseEntity<Blog>(result,HttpStatus.OK);
        return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Blog> AddNewBlog(@RequestBody Blog model){
        Blog result=adminServices.insertOrUpdateBlog(model);
        if(result!=null)
            return new ResponseEntity<Blog>(result,HttpStatus.OK);
        return new ResponseEntity<Blog>(HttpStatus.BAD_REQUEST);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Blog> EditBlog(@PathVariable String id, @RequestBody Blog model){
        Blog result=adminServices.insertOrUpdateBlog(model);
        if(result!=null)
            return new ResponseEntity<Blog>(result,HttpStatus.OK);
        return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
    }


}
