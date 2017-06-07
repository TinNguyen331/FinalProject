package com.kltn.controllers;

import com.kltn.bo.PriceByDayDTO;
import com.kltn.entities.PriceByDay;
import com.kltn.entities.Product;
import com.kltn.repositories.ProductRepository;
import com.kltn.services.AdminServices;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;
    //:GET
    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<PriceByDay>> GetAllPriceByDay(){
        return new ResponseEntity<List<PriceByDay>>(adminServices.getAllPriceByDay(), HttpStatus.OK);
    }
    /*@RequestMapping(path = {"/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<List<PriceByDay>> GetAllPriceByDayByProductId(@PathVariable String id){


    }*/

    //:POST
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PriceByDay> AddNewPriceByDay(@RequestBody PriceByDayDTO model){
        PriceByDay priceByDay=convertFromDTO(model);
        PriceByDay result=adminServices.insertOrUpdatePriceByDay(priceByDay);
        if(result!=null)
            return new ResponseEntity<PriceByDay>(result,HttpStatus.OK);
        return new ResponseEntity<PriceByDay>(HttpStatus.BAD_REQUEST);
    }

    private PriceByDay convertFromDTO(PriceByDayDTO priceByDayDTO){

        Product pro=productRepository.findOne(new ObjectId(priceByDayDTO.getProductId()));
        PriceByDay priceByDay=new PriceByDay(pro,priceByDayDTO.getPrice());
        return priceByDay;
    }


}
