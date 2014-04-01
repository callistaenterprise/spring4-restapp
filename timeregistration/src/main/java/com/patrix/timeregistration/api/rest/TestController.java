package com.patrix.timeregistration.api.rest;

import com.patrix.timeregistration.business.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Profile(value = { "test"})
@Slf4j
@Controller
@RequestMapping(TestController.BASE_URI)
public class TestController {

    public static final String BASE_URI = "/test";

    @Autowired
    private TestService testService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> create() {
        log.debug("get:");
        testService.createTestData();
        return new ResponseEntity<String>("{ \"status\": \"OK\" }", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> delete() {
        log.debug("get:");
        testService.deleteTestData();
        return new ResponseEntity<String>("{ \"status\": \"OK\" }", HttpStatus.OK);
    }

}
