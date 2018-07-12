package com.ninox.delivery.service.impl;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ninox.delivery.document.Client;
import com.ninox.delivery.repository.ClientRepository;
import com.ninox.delivery.service.ClientService;
import com.ninox.delivery.utils.SendEmail;

@Service
public class ClientServiceImpl implements ClientService{

	private static final String DIRETORIO = "./";
	private static final String EXTENSAO_CSV= ".csv";
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private SendEmail sendEmail;
	
	@Override
	public List<Client> listarClientes() {
		
		return this.clientRepository.findAll();
	}

	@Override
	public Client salvar(Client client) {

		return this.clientRepository.save(client);
	}

	@Override
	public String gerarCsv(Client client) throws IOException {
		
		String arquivo = client.getNome() + EXTENSAO_CSV;
		
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(DIRETORIO + arquivo));CSVPrinter csvPrinter = new CSVPrinter(writer,
						CSVFormat.DEFAULT.withHeader("ID", "Name", "Sobrenome"));) 
		{
			csvPrinter.printRecord(client.getId(), client.getNome(), client.getSobrenome());

			csvPrinter.flush();
		}
		
		return arquivo;
	}

	@Override
	public Optional<Client> clientById(String id) {
		return this.clientRepository.findById(id);
	}

	@Override
	public String sendEmail(Client client) {
		
		return sendEmail.simpleEmail(client);
	}
}
