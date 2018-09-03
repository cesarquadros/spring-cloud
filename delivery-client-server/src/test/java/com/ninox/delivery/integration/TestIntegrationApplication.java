package com.ninox.delivery.integration;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ninox.delivery.DeliveryClientServerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeliveryClientServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:application.yml")
public class TestIntegrationApplication {

	@BeforeClass
	public static void init() {
		WireMockUtils.initWireMockServer();
	}
	
    @AfterClass
    public static void afterClass() {
        WireMockUtils.stopWireMockServer();
    }
	
	@Test
	public void contextLoads() {
		Assert.assertTrue(true);
	}
}
