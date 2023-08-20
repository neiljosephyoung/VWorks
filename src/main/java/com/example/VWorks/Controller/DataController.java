package com.example.VWorks.Controller;

import com.example.VWorks.Service.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * @Author : Neil Young
 * @Description :
 * */
@RestController
@RequestMapping("/api/v1/Data")
public class DataController {

    private DataService dataService;
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
//    simpleJdbcCallFunction = new SimpleJdbcCall(linkJdbcTemplate).withFunctionName("IMP_MOVEMENT_INSERT");
//    SqlParameterSource sqlParams = new MapSqlParameterSource()
//            .addValue("OrderNo", docNum)
//            .addValue("Line", poItem)
//            .addValue("Product", material)
//            .addValue("Site", plant)
//            .addValue("Ref1", stgeLoc)
//            .addValue("Qty", entryQnt)
//            .addValue("MovementIndicator", mvtInd);
//
//                simpleJdbcCallFunction.execute(sqlParams);
}
