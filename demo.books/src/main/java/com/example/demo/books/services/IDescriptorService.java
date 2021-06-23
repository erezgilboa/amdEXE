package com.example.demo.books.services;

import entities.Descriptor;
import entities.ResultWithSuccess;

public interface IDescriptorService {

    ResultWithSuccess getDescriptorByName(String name);

    ResultWithSuccess saveOrUpdate(Descriptor descriptor);

}
