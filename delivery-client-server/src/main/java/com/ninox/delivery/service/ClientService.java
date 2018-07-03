package com.ninox.delivery.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.ninox.delivery.document.Client;

public interface ClientService {
	
	List<Client> listarClientes();
	Client salvar(Client client);
	Optional<Client> clientById(String id);
	String gerarCsv(Client client) throws IOException;
}	