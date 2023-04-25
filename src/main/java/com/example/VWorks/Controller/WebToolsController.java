package com.example.VWorks.Controller;

import com.example.VWorks.Service.WebToolsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
public class WebToolsController {

    WebToolsService webToolsService;
    public WebToolsController(WebToolsService webToolsService){
        this.webToolsService = webToolsService;
    }

    @PostMapping("/Scalp")
    public String scalpWebsite(@RequestBody String body){
        return webToolsService.scalpWebsite(body);
    }

}
