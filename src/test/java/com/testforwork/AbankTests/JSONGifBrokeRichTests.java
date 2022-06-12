package com.testforwork.AbankTests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.testforwork.AbankTests.feign.JSONGifBrokeRich;
import com.testforwork.AbankTests.mocks.GifBrokeRichMock;
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
public class JSONGifBrokeRichTests {
    @Autowired
    private WireMockServer mockExchangeService;

    @Autowired
    private JSONGifBrokeRich gifBrokeRichClient;

    @BeforeEach
    void setUp() throws IOException {
        GifBrokeRichMock.setupMockResponse(mockExchangeService);
        mockExchangeService.start();
    }

    @AfterEach
    void setDown(){
        mockExchangeService.stop();
    }

    @Test
    public void noEmptyTest() {
        Assertions.assertFalse(gifBrokeRichClient.getRich().isEmpty());
    }

    @Test
    public void wireMockTest() {
        String json = gifBrokeRichClient.getRich();
        String object = new JSONObject(json).getJSONObject("data").getString("id");
        Assertions.assertEquals(object, "MFO8414YmatCXigf8x");
    }
}
