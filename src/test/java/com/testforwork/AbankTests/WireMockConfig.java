package com.testforwork.AbankTests;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@ActiveProfiles("test")
public class WireMockConfig {

	@Autowired
	@Lazy
	private WireMockServer wireMockServer;

	@Bean(initMethod = "start", destroyMethod = "stop")
	public WireMockServer mockBooksService() {
		return new WireMockServer(8088);
	}

}
