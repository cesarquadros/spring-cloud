package com.ninox.delivery.contract;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ninox.delivery.document.Client;
import com.ninox.delivery.response.Response;

public interface ClientContract {

	@GetMapping(value = "/api/client", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Response<List<Client>>> listarTodos();
	
	@PostMapping(value = "/api/client", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Response<Client>> cadastrar(@Valid @RequestBody Client client, BindingResult result);
}
