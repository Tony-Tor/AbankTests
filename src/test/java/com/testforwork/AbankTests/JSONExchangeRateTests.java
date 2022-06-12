package com.testforwork.AbankTests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.testforwork.AbankTests.feign.JSONExchangeRate;
import com.testforwork.AbankTests.mocks.ExchangeRateMock;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { WireMockConfig.class })
@TestPropertySource("classpath:application_test.properties")
public class JSONExchangeRateTests {
    @Autowired
    private WireMockServer mockExchangeService;

    @Autowired
    private JSONExchangeRate exchangeClient;

    @BeforeEach
    void setUp() throws IOException {
        ExchangeRateMock.setupMockResponse(mockExchangeService);
        mockExchangeService.start();
    }

    @AfterEach
    void setDown(){
        mockExchangeService.stop();
    }

    @Test
    public void noEmptyTest() {
        Assertions.assertFalse(exchangeClient.getToday().isEmpty());
    }

    @Test
    public void wireMockTest() {
        String json = exchangeClient.getToday();
        JSONObject todayObject = new JSONObject(json).getJSONObject("rates");
        BigDecimal todayRate = todayObject.getBigDecimal("RUB");
        Assertions.assertEquals(todayRate, new BigDecimal("57.624893"));
    }
}
