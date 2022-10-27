package com.example.csw_api.controller;

import com.example.csw_api.entity.Product;
import com.example.csw_api.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    ProductServices productServices;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.ok(productServices.saveProduct(product));
    };

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(productServices.getAll());
    };


    @RequestMapping(value = "/Update/{id}/{amount}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> findById(@PathVariable int id, @PathVariable int amount){
        return ResponseEntity.ok(productServices.updateQuantity(id, amount));
    };
}
