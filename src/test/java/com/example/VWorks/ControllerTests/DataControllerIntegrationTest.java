package com.example.VWorks.ControllerTests;

import com.example.VWorks.Controller.DataController;
import com.example.VWorks.Service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataController.class)
public class DataControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataService dataService;

    @Test
    void shouldGetWelcomeMsg() throws Exception {
        when(dataService.getWelcomeMsg())
                .thenReturn(new ResponseEntity("Welcome!", HttpStatus.OK));
        mockMvc.perform(get("/api/v1/Welcome")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + "test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Welcome!")));
        verify(dataService).getWelcomeMsg();
    }
}
