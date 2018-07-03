package com.ninox.delivery.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ninox.delivery.contract.ClientContract;
import com.ninox.delivery.document.Client;
import com.ninox.delivery.response.Response;
import com.ninox.delivery.service.impl.ClientServiceImpl;
import com.wordnik.swagger.annotations.Api;

@Api(value = "TESTE", tags = "Teste")
@RestController
public class ClientController implements ClientContract{

	@Autowired
	private ClientServiceImpl clientServiceImpl;
	
	@Override
	public ResponseEntity<Response<List<Client>>> listarTodos() {
		
		return ResponseEntity.ok(new Response<List<Client>>(clientServiceImpl.listarClientes()));
	}

	@Override
	public ResponseEntity<Response<Client>> cadastrar(@RequestBody Client client) {
		return ResponseEntity.ok(new Response<Client>(clientServiceImpl.salvar(client)));
	}

	@Override
	public HttpEntity<byte[]> download(@PathVariable(name="id")String id) throws IOException {
		
		String nomeArquivo = this.clientServiceImpl.gerarCsv(this.clientServiceImpl.clientById(id).get());
		
		byte[] arquivo = Files.readAllBytes( Paths.get("./" + nomeArquivo));
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.add("Content-Disposition", "attachment;filename=\"" + nomeArquivo + "\"");
		
		return new HttpEntity<>(arquivo, httpHeaders);
		
	}

}
