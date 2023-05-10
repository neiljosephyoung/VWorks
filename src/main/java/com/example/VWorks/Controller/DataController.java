package com.example.VWorks.Controller;

import com.example.VWorks.Service.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Data")
public class DataController {

    DataService dataService;
    public DataController(DataService dataService){
        this.dataService = dataService;
    }

    @GetMapping("/Welcome")
    public ResponseEntity getWelcomeMsg(){
        return dataService.getWelcomeMsg();
    }

    @GetMapping("/Emoji")
    public ResponseEntity getEmoji(){
        return dataService.getEmoji();
    }

    @GetMapping("/CCodes")
    public ResponseEntity getCountryCodes(@RequestParam Optional<String> name,
                                          @RequestParam Optional<String> code) {
        if (name.isPresent()) {
            return dataService.findCountryByName(name.orElse(""));
        } else if(code.isPresent()){
            return dataService.findCountryByCode(code.orElse(""));
        } else {
            return dataService.getCountryCodes();
        }
    }

    @GetMapping("/test")
    public String test(@RequestParam(required = false) String test){
        return String.format("Here's the test query parameter : %s", test) ;
    }


}
