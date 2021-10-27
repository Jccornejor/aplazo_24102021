package com.jccornejor.exam.aplazo.creditpayments.controller;

import com.jccornejor.exam.aplazo.creditpayments.dto.CreditDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreditModelPaymentsControllerTest {

    private final String SERVER = "http://localhost:";
    private final String ENDPOINT = "/public/credit-payments";


    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeTestClass
    public void config() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        this.testRestTemplate.getRestTemplate().setMessageConverters(List.of(converter));
    }

    @Test
    void contextLoads() {
        assertNotNull(this.testRestTemplate);
    }

    @Test
    void shouldReturn415WhenSendingNullObjectRequestToEndpoint() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, null, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingEmptyObjectRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingEmptyAmountOnRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setRate(40d);
        credit.setTerms(12);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingAmountLESSThanOneOnRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setAmount(-40000d);
        credit.setRate(40d);
        credit.setTerms(12);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingAmountMOREThan_999999_OnRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setAmount(4000000d);
        credit.setRate(40d);
        credit.setTerms(12);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingEmptyRateOnRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setAmount(400000d);
        credit.setTerms(12);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingRateLESSThanOneOnRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setAmount(400000d);
        credit.setRate(-40d);
        credit.setTerms(12);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingRateMOREThanHundredOnRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setAmount(400000d);
        credit.setRate(400d);
        credit.setTerms(12);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingEmptyTermsOnRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setAmount(400000d);
        credit.setRate(400d);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingTermsLESSThanFOUROnRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setAmount(400000d);
        credit.setRate(-40d);
        credit.setTerms(2);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturn400WhenSendingTermsMOREThanFiftyTwoOnRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setAmount(400000d);
        credit.setRate(400d);
        credit.setTerms(62);

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void shouldReturnListWhenSendingRequestToEndpoint() throws Exception {

        final CreditDTO credit = new CreditDTO();
        credit.setAmount(40000d);
        credit.setRate(40d);
        credit.setTerms(12);
        @SuppressWarnings("rawtypes")
        ResponseEntity<List> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, List.class);
        assertNotNull(response);
        assertEquals(ArrayList.class, response.getBody().getClass());
    }

    @Test
    void shouldReturnListEqualsToTermsWhenSendingRequestToEndpoint() throws Exception {
        final CreditDTO credit = new CreditDTO();
        credit.setAmount(40000d);
        credit.setRate(40d);
        credit.setTerms(12);
        @SuppressWarnings("rawtypes")
        ResponseEntity<List> response = this.testRestTemplate.postForEntity(
                this.SERVER + this.port + this.ENDPOINT, credit, List.class);
        assertNotNull(response);
        assertEquals(credit.getTerms(), response.getBody().size());
    }
}