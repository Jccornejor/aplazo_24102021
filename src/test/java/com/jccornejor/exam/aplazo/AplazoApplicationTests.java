package com.jccornejor.exam.aplazo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
class AplazoApplicationTests {

    private final String SERVER = "http://localhost:";
    private final String MANAGEMENT_ENDPOINT = "/actuator";

    @LocalServerPort
    private int port;

    @Value("${local.management.port}")
    private int managementPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.getForEntity(
                this.SERVER + this.managementPort + this.MANAGEMENT_ENDPOINT, Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldNotReturn200WhenSendingRequestToEndpoint() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.getForEntity(
                this.SERVER + this.port + "/", Map.class);
        assertNotNull(response);
        assertNotEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldReturn200WhenSendingHealthRequestToManagementEndpoint() throws Exception {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = this.testRestTemplate.getForEntity(
                this.SERVER + this.managementPort + this.MANAGEMENT_ENDPOINT + "/health", Map.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void contextLoads() {
        assertNotNull(this.testRestTemplate);
    }

}
