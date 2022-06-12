package com.testforwork.AbankTests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.testforwork.AbankTests.controllers.BenefitsController;
import com.testforwork.AbankTests.exception.ServiceNotFoundException;
import com.testforwork.AbankTests.feign.GIFBrokeRich;
import com.testforwork.AbankTests.feign.JSONExchangeRate;
import com.testforwork.AbankTests.feign.JSONGifBrokeRich;
import com.testforwork.AbankTests.mocks.BrokeRichMock;
import com.testforwork.AbankTests.mocks.ExchangeRateMock;
import com.testforwork.AbankTests.mocks.GifBrokeRichMock;
import org.json.JSONException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = { WireMockConfig.class })
@TestPropertySource("classpath:application_test.properties")
public class BenefitsControllerTests {

    @Autowired
    private WireMockServer mockExchangeService;

    @Autowired
    private GIFBrokeRich brokeRichClient;

    @Autowired
    private JSONExchangeRate exchangeClient;

    @Autowired
    private JSONGifBrokeRich gifBrokeRichClient;

    @Autowired
    private BenefitsController benefitsController;

    @BeforeEach
    void setUp() throws IOException {
        BrokeRichMock.setupMockResponse(mockExchangeService);
        ExchangeRateMock.setupMockResponse(mockExchangeService);
        GifBrokeRichMock.setupMockResponse(mockExchangeService);
        mockExchangeService.start();
    }

    @AfterEach
    void setDown(){
        mockExchangeService.stop();
    }

    @Test()
    void wrongRequest() {
        try {
            benefitsController.getBenefitsGif("dsfdfsfds");
        }catch (ServiceNotFoundException e){
            Assertions.assertTrue(true);
            return;
        }
        Assertions.fail();

    }

    @Test
    void rightRequest() {
        byte[] bytes = benefitsController.getBenefitsGif("RUB");
        try {
            byte[] bytesExpected = StreamUtils.copyToString(
                    ExchangeRateMock.class.getClassLoader().getResourceAsStream("MFO8414YmatCXigf8x.gif"),
                    Charset.defaultCharset()).getBytes(StandardCharsets.UTF_8);
            Assertions.assertArrayEquals(bytesExpected, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
