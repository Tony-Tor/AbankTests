package com.testforwork.AbankTests.mocks;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@ActiveProfiles("test")
public class BrokeRichMock {
    public static void setupMockResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/MFO8414YmatCXigf8x.gif"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.IMAGE_GIF_VALUE)
                        .withBody(
                                StreamUtils.copyToString(
                                        ExchangeRateMock.class.getClassLoader().getResourceAsStream("MFO8414YmatCXigf8x.gif"),
                                        Charset.defaultCharset()))));
    }
}
