package com.kltn.controllers;

import com.kltn.bo.ProductDTO;
import com.kltn.entities.Category;
import com.kltn.entities.Product;
import com.kltn.entities.User;
import com.kltn.services.AdminServices;
import com.kltn.services.CustomerServices;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @Autowired
    private ModelMapper modelMapper;

    //:GET
    @RequestMapping(path = {"/count"},method = {RequestMethod.GET})
    public ResponseEntity<Long> GetCounterProduct(){
        return new ResponseEntity<Long>(adminServices.countProduct(), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<List<Product>> GetAllProduct(){
        return new ResponseEntity<List<Product>>(customerServices.getAllProduct(), HttpStatus.OK);
    }

    //id is number get product
    @RequestMapping(path = {"/random/{id}"},method = {RequestMethod.GET})
    public ResponseEntity<List<Product>> GetProductRandom(@PathVariable int id){
        return new ResponseEntity<List<Product>>(customerServices.getRandom(id), HttpStatus.OK);
    }

    @RequestMapping(path = {"/inevent"},method = {RequestMethod.GET})
    public ResponseEntity<List<Product>> GetProductInEvent(){
        return new ResponseEntity<List<Product>>(customerServices.getAllProduct(), HttpStatus.OK);
    }

    @RequestMapping(path = {"/new"},method = {RequestMethod.GET})
    public ResponseEntity<List<Product>> GetNewProduct(){
        return new ResponseEntity<List<Product>>(customerServices.getAllNewProduct(),HttpStatus.OK);
    }

    @RequestMapping(path = {"/bestseller"},method = {RequestMethod.GET})
    public ResponseEntity<List<Product>> GetBestSeller(){
        return new ResponseEntity<List<Product>>(customerServices.getAllNewProduct(),HttpStatus.OK);
    }

    @RequestMapping(path = {"/maybeyoulike"},method = {RequestMethod.GET})
    public ResponseEntity<List<Product>> GetProductMayBeUserLike(Principal principal){
        User user=adminServices.getUserByName(principal.getName());
        return new ResponseEntity<List<Product>>(customerServices.getAllProductMayBeUserLike(user),HttpStatus.OK);
    }
    @RequestMapping(path = {"/discountproduct/{id}"})
    public ResponseEntity<Double> GetDiscountProduct(@PathVariable String id){
        return new ResponseEntity<Double>(customerServices.getDiscountProductInEvent(id),HttpStatus.OK);
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
    public ResponseEntity<Product> AddNewProduct(@RequestBody ProductDTO model){
        Product pro=convertFromDTO(model);
        pro.setNew(true);
        Product result=adminServices.insertOrUpdateProduct(pro);
        if(result!=null)
            return new ResponseEntity<Product>(result,HttpStatus.OK);
        return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        //return new ResponseEntity<String>("success",HttpStatus.OK);
    }

    //:PUT
    @RequestMapping(path={"/{id}"},method = {RequestMethod.PUT} ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> EditProduct(@PathVariable String id, @RequestBody ProductDTO model){
        Product pro=convertFromDTO(model);
        //pro.setId(new ObjectId(id));
        Product result=adminServices.insertOrUpdateProduct(pro);
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

    private Product convertFromDTO(ProductDTO productDTO){
        Product pro=modelMapper.map(productDTO,Product.class);
        if(!productDTO.getId().isEmpty()) {
            pro.setId(new ObjectId(productDTO.getId()));
        }
        Category category=customerServices.getCategoryById(new ObjectId(productDTO.getCategoryId()));
        pro.setCategoryId(category);
        return pro;
    }
}

