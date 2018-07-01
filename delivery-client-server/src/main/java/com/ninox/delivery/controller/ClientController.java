package com.ninox.delivery.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
	
	@RequestMapping(method = RequestMethod.GET)
    public String findAll(){
		System.out.println("get client");
        return "teste";
    }
}
