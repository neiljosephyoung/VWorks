package com.example.VWorks.Controller;

import com.example.VWorks.Service.AlgorithmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AlgorithmController {

    AlgorithmService algorithmService;
    public AlgorithmController(AlgorithmService algorithmService){
        this.algorithmService = algorithmService;
    }
    @PostMapping("/MergeSort")
    public ResponseEntity mergeSort(@RequestBody String body){
        return algorithmService.mergeSort(body);
    }

    @PostMapping("/BubbleSort")
    public ResponseEntity bubbleSort(@RequestBody String body){
        return algorithmService.bubbleSort(body);
    }

    @PostMapping("/QuickSort")
    public ResponseEntity quickSort(@RequestBody String body){
        return algorithmService.quickSort(body);
    }
}
