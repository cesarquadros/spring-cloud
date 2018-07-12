package com.ninox.delivery.contract;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ninox.delivery.document.Client;
import com.ninox.delivery.response.Response;

public interface ClientContract {

	@GetMapping(value = "/api/client", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Response<List<Client>>> listarTodos();
	
	@PostMapping(value = "/api/client", consumes =MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Response<Client>> cadastrar(@RequestBody Client client);
	
	@GetMapping(value = "api/client/sendemail/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Response<String> sendEmail(@PathVariable(name="id")String id);
	
	@GetMapping(value = "/api/client/downloadcsv/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	HttpEntity<byte[]> download(@PathVariable(name="id")String id) throws IOException;
}
