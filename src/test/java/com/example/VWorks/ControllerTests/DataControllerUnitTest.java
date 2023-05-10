package com.example.VWorks.ControllerTests;

import com.example.VWorks.Controller.DataController;
import com.example.VWorks.Service.DataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

public class DataControllerUnitTest {

    DataController dataController;

    @BeforeEach
    void setup(){
        DataService dataService = Mockito.mock(DataService.class);
        when(dataService.getWelcomeMsg())
                .thenReturn(new ResponseEntity("Welcome!", HttpStatus.OK));
        this.dataController = new DataController(dataService);
    }

    @Test
    void shouldGetWelcome(){
        assertEquals(new ResponseEntity("Welcome!", HttpStatus.OK), dataController.getWelcomeMsg());
    }
    @Test
    void shouldGetWelcomeFAIL(){
        assertNotEquals(new ResponseEntity("Welcome FAIL!", HttpStatus.OK), dataController.getWelcomeMsg());
    }
}
