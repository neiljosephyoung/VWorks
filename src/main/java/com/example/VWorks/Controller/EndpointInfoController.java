package com.example.VWorks.Controller;

import com.example.VWorks.Service.EndpointInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EndpointInfoController {
    EndpointInfoService endpointInfoService;
    public EndpointInfoController(EndpointInfoService endpointInfoService){
        this.endpointInfoService = endpointInfoService;
    }

    @GetMapping("/Endpoints")
    public Map<String, Map<String, String>> getEndpoints() {
        return endpointInfoService.getAllEndpoints();
    }

}
