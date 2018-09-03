package com.ninox.delivery.integration;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import com.ninox.delivery.rest.ClientController;

public class ClientRestTest extends TestIntegrationApplication {

	private MockMvc mockMvc;

	private String baseUri;
	
	@Value("${local.server.port}")
	public int serverPort;
	
	@Before
	public void setUp() {
		ClientController ClientController = new ClientController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(ClientController).build();
		RestAssured.port = serverPort;
		RestAssured.defaultParser = Parser.JSON;
	}

	@Test
	public void testGetClient() throws Exception {

		RestAssured
		.given()
		.when()
		.get("/client")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);

		/*
		 * this.mockMvc.perform(MockMvcRequestBuilders.get("/client"))
		 * .andExpect(MockMvcResultMatchers.status().isOk());
		 */
	}
}
