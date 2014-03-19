package com.patrix.timeregistration.api.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static com.patrix.timeregistration.api.rest.TimeRegistrationController.BASE_URI;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import com.patrix.timeregistration.api.message.TimeLogMessage;
import com.patrix.timeregistration.business.TimeRegistrationService;

public class TimeRegistrationIntegrationTest {

    MockMvc mockMvc;

    @InjectMocks
    TimeRegistrationController controller;

    @Mock
    TimeRegistrationService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }


    @Test
    public void thatFindAllTimeLogMessagesWorks() throws Exception {
        when(service.findAllTimeLogMessages()).thenReturn(Arrays.asList(new TimeLogMessage()));

        this.mockMvc.perform(
                get(BASE_URI)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void thatAddTimeLogMessageWorks() throws Exception {
        final TimeLogMessage timeLogMessage = new TimeLogMessage();
        when(service.addTimeLogMessage(timeLogMessage)).thenReturn(timeLogMessage);

        this.mockMvc.perform(
                post(BASE_URI)
                        .content("{ \"title\": \"title\", \"caseNumber\": \"caseNumber\", \"workCodeId\": \"workCodeId\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void thatAddDeleteLogMessageWorks() throws Exception {
        final TimeLogMessage timeLogMessage = new TimeLogMessage();
        final String id = "FakeID";
        timeLogMessage.setId(id);
        when(service.deleteTimeLogMessage(id)).thenReturn(timeLogMessage);

        this.mockMvc.perform(
                delete(BASE_URI  + "/{1}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
