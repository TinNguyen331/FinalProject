package com.kltn.controllers;

import com.kltn.bo.Login;
import com.kltn.bo.UserDTO;
import com.kltn.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TinNguyen on 6/4/17.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserDTO> test(){
        return new ResponseEntity<UserDTO>(new UserDTO(), HttpStatus.OK);
    }
}
