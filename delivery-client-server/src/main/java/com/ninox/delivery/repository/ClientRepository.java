package com.ninox.delivery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ninox.delivery.document.Client;

public interface ClientRepository extends MongoRepository<Client, String>{
	
}
