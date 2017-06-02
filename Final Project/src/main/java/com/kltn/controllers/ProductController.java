package com.kltn.controllers;

import com.kltn.entities.Product;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private AdminServices adminServices;

    //:GET
    @RequestMapping(path = {"/count"},method = {RequestMethod.GET})
    public ResponseEntity<Long> GetCounterProduct(){
        return new ResponseEntity<Long>(adminServices.countProduct(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<Product>> GetAllProduct(){
        return new ResponseEntity<List<Product>>(customerServices.getAllProduct(), HttpStatus.OK);
    }

    @RequestMapping(path = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<Product> GetProductById(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        Product result=customerServices.getProductById(objectId);
        if(result!=null)
            return new ResponseEntity<Product>(result,HttpStatus.OK);
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(path = {"/category/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<List<Product>> GetProductByCateId(@PathVariable String id){
        ObjectId objectId=new ObjectId(id);
        List<Product> result=customerServices.getProductByCategoryId(objectId);
        if(!result.isEmpty())
            return new ResponseEntity<List<Product>>(result,HttpStatus.OK);
        return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
    }
    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> AddNewProduct(@RequestBody Product model){
        Product result=adminServices.insertOrUpdateProduct(model);
        if(result!=null)
            return new ResponseEntity<Product>(result,HttpStatus.OK);
        return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> EditProduct(@PathVariable String id, @RequestBody Product model){
        Product result=adminServices.insertOrUpdateProduct(model);
        if(result!=null)
            return new ResponseEntity<Product>(result,HttpStatus.OK);
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }

    //:DELETE
    @RequestMapping(path = {"/{id}"},method = {RequestMethod.DELETE})
    public ResponseEntity<Boolean> DeleteProduct(@PathVariable String  id){
        ObjectId objectId=new ObjectId(id);
        boolean result=adminServices.deleteProduct(objectId);
        if(result)
            return new ResponseEntity<Boolean>(result,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }
}

