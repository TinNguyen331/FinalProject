package com.kltn.controllers;

import com.kltn.entities.Category;
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
 * Created by TinNguyen on 5/4/17.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("categories")
public class CategoryController {


    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AdminServices adminServices;

    //:GET
    @RequestMapping(path = {"/count"},method = {RequestMethod.GET})
    public ResponseEntity<Long> GetCounterCategory(){
        return new ResponseEntity<Long>(adminServices.countCategory(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<Category>> GetAllCategories(){
        return new ResponseEntity<List<Category>>(customerServices.getAllCategory(), HttpStatus.OK);
    }

    @RequestMapping(path = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<Category> GetEventById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        Category result=customerServices.getCategoryById(objectId);
        if(result!=null)
            return new ResponseEntity<Category>(result,HttpStatus.OK);
        return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
    }

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Category> AddNewCategory(@RequestBody Category model){
        Category result=adminServices.insertOrUpdateCategory(model);
        if(result!=null)
            return new ResponseEntity<Category>(result,HttpStatus.OK);
        return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Category> EditCategory(@PathVariable String id, @RequestBody Category model){
        Category result=adminServices.insertOrUpdateCategory(model);
        if(result!=null)
            return new ResponseEntity<Category>(result,HttpStatus.OK);
        return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
    }

    //:DELETE
    @RequestMapping(path = {"/{id}"},method = {RequestMethod.DELETE})
    public ResponseEntity<Boolean> DeleteCategory(@PathVariable String  id){
        ObjectId objectId=new ObjectId(id);
        boolean result=adminServices.deleteCategory(objectId);
        if(result)
            return new ResponseEntity<Boolean>(result,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }

}
