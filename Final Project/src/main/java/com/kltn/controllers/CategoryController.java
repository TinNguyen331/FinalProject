package com.kltn.controllers;

import com.kltn.entities.Category;
import com.kltn.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TinNguyen on 5/4/17.
 */
@RestController
@RequestMapping("categories")
public class CategoryController {


    @Autowired
    private CustomerServices customerServices;

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<Category>> GetAllCategories(){
        return new ResponseEntity<List<Category>>(customerServices.getAllCategory(), HttpStatus.OK);
    }

}
