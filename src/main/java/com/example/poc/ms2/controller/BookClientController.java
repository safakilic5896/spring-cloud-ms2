package com.example.poc.ms2.controller;

import com.example.poc.ms2.model.BookDto;
import com.example.poc.ms2.service.BookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookClientController {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> getAllBook() {
        // PRINT ALL MICROSERVICE REGISTERED
        System.out.println(discoveryClient.getServices());
        return ResponseEntity.ok(bookClient.getAllBook());
    }
}
