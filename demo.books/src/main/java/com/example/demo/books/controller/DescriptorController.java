package com.example.demo.books.controller;

import com.example.demo.books.services.IDescriptorService;
import entities.Descriptor;
import entities.ResultWithSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AddressConstants.DescriptorEndPoint)
public class DescriptorController {

    @Autowired
    private IDescriptorService descriptorService;

    @RequestMapping(method = RequestMethod.POST, value = AddressConstants.saveOrUpdate)
    public ResponseEntity<ResultWithSuccess> saveOrUpdate(@RequestBody Descriptor entity) {
        ResultWithSuccess saved = descriptorService.saveOrUpdate(entity);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = AddressConstants.findByName + "/{bookName}")
    public ResponseEntity<ResultWithSuccess> findByName(@PathVariable("bookName") String bookName) {
        ResultWithSuccess resultWithSuccess = descriptorService.getDescriptorByName(bookName);
        return new ResponseEntity<>(resultWithSuccess, HttpStatus.OK);
    }

}
