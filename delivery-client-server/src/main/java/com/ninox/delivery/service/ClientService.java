package com.ninox.delivery.service;

import java.util.List;

import com.ninox.delivery.document.Client;

public interface ClientService {
	
	List<Client> listarClientes();
	Client salvar(Client client);
}