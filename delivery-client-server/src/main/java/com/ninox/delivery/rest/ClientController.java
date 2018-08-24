package com.ninox.delivery.rest;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ninox.delivery.contract.ClientContract;
import com.ninox.delivery.document.Client;
import com.ninox.delivery.response.Response;
import com.ninox.delivery.service.impl.ClientServiceImpl;
import com.wordnik.swagger.annotations.Api;

@RestController
public class ClientController implements ClientContract {

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
	public HttpEntity<byte[]> download(@PathVariable(name = "id") String id) throws IOException {

		String nomeArquivo = this.clientServiceImpl.gerarCsv(this.clientServiceImpl.clientById(id).get());

		byte[] arquivo = Files.readAllBytes(Paths.get("./" + nomeArquivo));
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("Content-Disposition", "attachment;filename=\"" + nomeArquivo + "\"");

		return new HttpEntity<>(arquivo, httpHeaders);

	}

	@Override
	public Response<String> sendEmail(@PathVariable(name = "id") String id) {

		List<String> mensagem = new ArrayList<>();

		Client client = clientServiceImpl.clientById(id).get();

		Response<String> response = new Response<>(null);

		if (!ObjectUtils.isEmpty(client)) {
			mensagem.add(clientServiceImpl.sendEmail(client));
		}

		response.setErros(mensagem);

		return response;
	}

	@Override
	public HttpEntity<byte[]> downloadArquivo() throws IOException {

		byte[] arquivo = Files.readAllBytes(Paths.get("/opt/upload/in/teste.pdf"));

		HttpHeaders httpHeaders = new HttpHeaders();

		String nomeArquivo = "arquivoTeste";
		
		httpHeaders.add("Content-Disposition", "attachment;filename=\"" + nomeArquivo  + "\"");

		return new HttpEntity<>(arquivo, httpHeaders);
	}

	@Override
	public ResponseEntity downloadCsv(@Context HttpServletResponse response, @PathVariable(name = "id") String id) {

		try {

			List<Client> list = new ArrayList<>();

			list.add(this.clientServiceImpl.clientById(id).get());

			// setting the content disposition
			response.setHeader("Content-Disposition",
					"attachment; filename=" + "report_" + System.currentTimeMillis() + ".csv");
			// marking content type as csv
			response.setContentType("text/csv");
			// retrieving output stream writer from the response
			OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");

			// Initializing Apache Commons Csv format

			// Create the CSVFormat object with "\n" as a record delimiter
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withDelimiter(',').withRecordSeparator("\n");

			// Initializing Apache Commons Csv printer

			CSVPrinter csvFilePrinter = new CSVPrinter(osw, csvFileFormat);

			// printing header to the csv file
			csvFilePrinter.printRecord(Client.class);

			for (Client client : list) {
				List<String> row = new ArrayList<String>();
				// adding columns
				row.add(client.getId());
				row.add(client.getNome());
				// printing rows
				csvFilePrinter.printRecord(row);
			}

			csvFilePrinter.flush();
			csvFilePrinter.close();

			osw.flush();
			osw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok().build();
	}

}
