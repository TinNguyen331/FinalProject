package com.kltn.controllers;

import com.kltn.bo.ImportModelDTO;
import com.kltn.entities.Import;
import com.kltn.entities.ImportModel;
import com.kltn.services.AdminServices;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TinNguyen on 6/9/17.
 */
@RestController
@RequestMapping(path = "/import")
public class ImportController {

    @Autowired
    private AdminServices adminServices;
    @Autowired
    private CustomerServices customerServices;
    @Autowired
    private ModelMapper modelMapper;

    //:POST
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = {RequestMethod.POST} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> AddNewCategory(@RequestBody List<ImportModelDTO> model){
//        Import result=adminServices.insertOrUpdateCategory(model);
//        if(result!=null)
//            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//        return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
        List<ImportModel> lst=convertFromDTO(model);
        Import result=adminServices.insertOrUpdateImport(lst);
        if(result!=null)
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
    }

    private List<ImportModel> convertFromDTO(List<ImportModelDTO> modelDTOS){
        List<ImportModel> lst=new ArrayList<>();
        for (ImportModelDTO dto:modelDTOS
             ) {
            ImportModel importModel=modelMapper.map(dto,ImportModel.class);
            importModel.setProductId(customerServices.getProductById(new ObjectId(dto.getProductId())));
            lst.add(importModel);
        }
        return lst;
    }
}
