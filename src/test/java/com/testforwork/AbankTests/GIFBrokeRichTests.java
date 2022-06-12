package com.testforwork.AbankTests;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.testforwork.AbankTests.feign.GIFBrokeRich;
import com.testforwork.AbankTests.mocks.BrokeRichMock;
import com.testforwork.AbankTests.mocks.ExchangeRateMock;
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
public class GIFBrokeRichTests {

    @Autowired
    private WireMockServer mockExchangeService;

    @Autowired
    private GIFBrokeRich brokeRichClient;



    @BeforeEach
    void setUp() throws IOException {
        BrokeRichMock.setupMockResponse(mockExchangeService);
        mockExchangeService.start();
    }

    @AfterEach
    void setDown(){
        mockExchangeService.stop();
    }

    @Test
    public void noEmptyTest() {
        Assertions.assertTrue(brokeRichClient.getGif("MFO8414YmatCXigf8x").length > 0);
    }

    @Test
    public void equalsTest() {
        byte[] bytes = brokeRichClient.getGif("MFO8414YmatCXigf8x");

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
