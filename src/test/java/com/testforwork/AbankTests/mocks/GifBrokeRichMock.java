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
public class GifBrokeRichMock {
    public static void setupMockResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/v1/gifs/random?api_key=testappid&tag=rich&rating=g"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                StreamUtils.copyToString(
                                        ExchangeRateMock.class.getClassLoader().getResourceAsStream("giphyMock.json"),
                                        Charset.defaultCharset()))));
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/v1/gifs/random?api_key=testappid&tag=broke&rating=g"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                StreamUtils.copyToString(
                                        ExchangeRateMock.class.getClassLoader().getResourceAsStream("giphyMock.json"),
                                        Charset.defaultCharset()))));
    }
}
