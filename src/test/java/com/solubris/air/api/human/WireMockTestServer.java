package com.solubris.air.api.human;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.junit.Stubbing;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Base class to allow running tests which require a wire mock server.
 * This is an alternative to using the WireMockExtension.
 * Because the WireMockExtension doesn't suit use of a random port in combination with a declarative http client.
 */
public abstract class WireMockTestServer {
    private final WireMockServer wireMock;
    private final ResourceLoader resourceLoader;
    private final JsonMapper jsonMapper;

    protected WireMockTestServer(int port) {
        this.wireMock = new WireMockServer(options().port(port));
        this.resourceLoader = null;
        this.jsonMapper = null;
    }

    protected WireMockTestServer(ResourceLoader resourceLoader, JsonMapper jsonMapper, int port) {
        this.wireMock = new WireMockServer(options().port(port));
        this.resourceLoader = resourceLoader;
        this.jsonMapper = jsonMapper;
    }

    @BeforeEach
    public void startWireMock() {
        wireMock.start();
    }

    @AfterEach
    public void stopWireMock() {
        wireMock.stop();
    }

    public Stubbing wireMockStub() {
        return wireMock;
    }

//    @CanIgnoreReturnValue
    public StubMapping wireMockStubFor(MappingBuilder mappingBuilder) {
        return wireMock.stubFor(mappingBuilder);
    }

    public String fromFile(String path) throws IOException {
        return resourceLoader.getResource(path).getContentAsString(UTF_8);
    }

    public <T> void stubPostFor(String url, T request, String responseBodyFilePath) throws IOException {
        String value = jsonMapper.writeValueAsString(request);
        wireMockStubFor(
                post(urlPathEqualTo(url))
                        .withRequestBody(equalToJson(value, true, true))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(fromFile(responseBodyFilePath))));
    }

    public void stubGetFor(String url,
                           Map<String, StringValuePattern> params,
                           Map.Entry<String, String> header,
                           String responseBodyFilePath, Integer delay) throws IOException {
        wireMockStubFor(
                get(urlPathEqualTo(url))
                        .withQueryParams(params)
                        .withHeader(header.getKey(), equalTo(header.getValue()))
                        .willReturn(
                                aResponse()
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(fromFile(responseBodyFilePath))
                                        .withFixedDelay(delay)));
    }
}