package com.ninox.delivery.contract;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ninox.delivery.document.Client;
import com.ninox.delivery.response.Response;

public interface ClientContract {

	@GetMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Response<List<Client>>> listarTodos();
	
	@PostMapping(value = "/client", consumes =MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Response<Client>> cadastrar(@RequestBody Client client);
	
	@GetMapping(value = "/client/sendemail/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Response<String> sendEmail(@PathVariable(name="id")String id);
	
	@GetMapping(value = "/client/downloadcsv/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	HttpEntity<byte[]> download(@PathVariable(name="id")String id) throws IOException;
	
	@GetMapping(value = "/client/downloadarquivo", produces = MediaType.APPLICATION_JSON_VALUE)
	HttpEntity<byte[]> downloadArquivo() throws IOException;
	
	@GetMapping(value="/client/downcsv/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity downloadCsv(@Context HttpServletResponse response, @PathVariable(name="id")String id);
	
}
