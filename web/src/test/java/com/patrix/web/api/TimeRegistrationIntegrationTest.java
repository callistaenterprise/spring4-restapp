package com.patrix.web.api;

import com.patrix.timeregistration.api.rest.TimeRegistrationController;
import com.patrix.util.api.ErrorMessage;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.patrix.timeregistration.api.message.TimeLogMessage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TimeRegistrationIntegrationTest {

    private TimeLogMessage timeLogMessage;
    private RestTemplate restTemplate;

    public static final String BASE_URL = "http://localhost:8080" + TimeRegistrationController.BASE_URI;
    public static final String USER_AUTH = "user:test";

    public static final ResponseErrorHandler NOOP_HANDLER = new ResponseErrorHandler() {
        @Override
        public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

        }
    };

    @Before
    public void before() {
        timeLogMessage = new TimeLogMessage();
        timeLogMessage.setCaseNumber("caseNumber");
        timeLogMessage.setTitle("title");
        timeLogMessage.setWorkCodeId("workCodeId");

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    //
    HttpHeaders getHeaders(String auth) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
        httpHeaders.add("Authorization", "Basic " + new String(encodedAuthorisation));
        return httpHeaders;
    }

    //
    ResponseEntity<TimeLogMessage> addTimeLogMessage(final String auth) {
        final HttpEntity<TimeLogMessage> requestEntity = new HttpEntity<TimeLogMessage>(timeLogMessage, getHeaders(auth));

        final ResponseEntity<TimeLogMessage> responseEntity = restTemplate.postForEntity(
                BASE_URL,
                requestEntity, TimeLogMessage.class);

         return responseEntity;
    }

    ResponseEntity<TimeLogMessage[]> getAllTimeLogMessages() {
        final HttpEntity<?> requestEntity = new HttpEntity<String>(getHeaders(USER_AUTH));
        return restTemplate.exchange(BASE_URL, HttpMethod.GET, requestEntity, TimeLogMessage[].class);
    }

    @Test
    public void thatTimeLogCanBeAdded() {

        final ResponseEntity<TimeLogMessage> responseEntity = addTimeLogMessage(USER_AUTH);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        final TimeLogMessage responseTimeLogMessage = responseEntity.getBody();
        assertNotNull(responseTimeLogMessage.getId());
    }

    @Test
    public void thatTimeLogCanBeDeleted() {
        final ResponseEntity<TimeLogMessage> addedEntity = addTimeLogMessage(USER_AUTH);
        final ResponseEntity<TimeLogMessage[]> beforeList = getAllTimeLogMessages();
        final HttpEntity<?> requestEntity = new HttpEntity<String>(getHeaders(USER_AUTH));
        restTemplate.exchange(BASE_URL + "/" + addedEntity.getBody().getId(), HttpMethod.DELETE, requestEntity, TimeLogMessage.class);
        final ResponseEntity<TimeLogMessage[]> afterList = getAllTimeLogMessages();
        assertTrue(beforeList.getBody().length > 0);
        assertEquals(beforeList.getBody().length - 1, afterList.getBody().length);
    }

    @Test(expected = HttpClientErrorException.class)
    public void thatAuthWorks() {
        final ResponseEntity<TimeLogMessage> responseEntity = addTimeLogMessage("user:invalid");
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void thatErrorHandlingWorks() {
        timeLogMessage.setTitle(null);

        restTemplate.setErrorHandler(NOOP_HANDLER);

        final HttpEntity<TimeLogMessage> requestEntity = new HttpEntity<TimeLogMessage>(timeLogMessage, getHeaders(USER_AUTH));

        final ResponseEntity<ErrorMessage> responseEntity = restTemplate.postForEntity(
                BASE_URL,
                requestEntity, ErrorMessage.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(-1, responseEntity.getBody().getCode());
        assertEquals("Mandatory field title is missing", responseEntity.getBody().getText());
    }
}
