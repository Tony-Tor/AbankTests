package com.testforwork.AbankTests.mocks;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;

@ActiveProfiles("test")
public class ExchangeRateMock {
    public static void setupMockResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/latest.json?" +
                        "app_id=testappid"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                StreamUtils.copyToString(
                                        ExchangeRateMock.class.getClassLoader().getResourceAsStream("exchangeRateMock.json"),
                                        Charset.defaultCharset()))));
        String yesterday = LocalDate.now().minusDays(1).toString();
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/historical/"+yesterday+".json?" +
                        "app_id=testappid"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                StreamUtils.copyToString(
                                        ExchangeRateMock.class.getClassLoader().getResourceAsStream("exchangeRateMock.json"),
                                        Charset.defaultCharset()))));
    }

}
