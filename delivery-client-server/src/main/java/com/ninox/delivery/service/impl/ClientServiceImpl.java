package com.ninox.delivery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninox.delivery.document.Client;
import com.ninox.delivery.repository.ClientRepository;
import com.ninox.delivery.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public List<Client> listarClientes() {
		
		return this.clientRepository.findAll();
	}

	@Override
	public Client salvar(Client client) {

		return this.salvar(client);
	}
}
