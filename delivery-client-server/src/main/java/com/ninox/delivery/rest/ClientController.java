package com.ninox.delivery.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.ninox.delivery.contract.ClientContract;
import com.ninox.delivery.document.Client;
import com.ninox.delivery.response.Response;
import com.ninox.delivery.service.impl.ClientServiceImpl;

@RestController
public class ClientController implements ClientContract{

	@Autowired
	private ClientServiceImpl clientServiceImpl;
	
	@Override
	public ResponseEntity<Response<List<Client>>> listarTodos() {
		
		return ResponseEntity.ok(new Response<List<Client>>(clientServiceImpl.listarClientes()));
	}

	@Override
	public ResponseEntity<Response<Client>> cadastrar(@Valid Client client, BindingResult result) {
		return ResponseEntity.ok(new Response<Client>(clientServiceImpl.salvar(client)));
	}
}
