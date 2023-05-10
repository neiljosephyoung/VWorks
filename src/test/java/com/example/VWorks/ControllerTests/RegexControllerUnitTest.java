package com.example.VWorks.ControllerTests;

import com.example.VWorks.Controller.RegexController;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegexControllerUnitTest {

    @Test
    public void testCheckEmailRegex() {
        RegexController regexController = new RegexController();
        String input = "test@example.com";
        ResponseEntity responseEntity = regexController.checkEmailRegex(input);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Input matches regex pattern.", responseEntity.getBody());
    }

    @Test
    public void testCheckEmailRegexBadRequest() {
        RegexController regexController = new RegexController();
        String input = "testexample.com";
        ResponseEntity responseEntity = regexController.checkEmailRegex(input);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Input does not match regex pattern.", responseEntity.getBody());

    }

    @Test
    public void testCheckWebsiteRegex() {
        RegexController regexController = new RegexController();
        String input = "https://www.example.com";
        ResponseEntity responseEntity = regexController.checkWebsiteRegex(input);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testCheckWebsiteRegexBadRequest() {
        RegexController regexController = new RegexController();
        String input = "example@.com";
        ResponseEntity responseEntity = regexController.checkWebsiteRegex(input);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Input does not match regex pattern.", responseEntity.getBody());
    }
}

