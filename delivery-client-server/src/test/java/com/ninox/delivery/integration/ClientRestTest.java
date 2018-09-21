package com.ninox.delivery.integration;

import static org.hamcrest.Matchers.hasKey;

import java.util.Locale;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;

public class ClientRestTest extends TestIntegrationApplication {

	
	@Value("${local.server.port}")
	public int serverPort;
	
	@Before
	public void setUp() {
		RestAssured.port = serverPort;
		RestAssured.defaultParser = Parser.JSON;
		Locale.setDefault(new Locale("pt", "BR"));
	}

	@Test
	public void testGetClient() throws Exception {

		RestAssured
		.given()
		.when()
		.get("/client")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("$", hasKey("datas"));
	}
}
