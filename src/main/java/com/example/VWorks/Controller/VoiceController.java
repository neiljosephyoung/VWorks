package com.example.VWorks.Controller;

import com.example.VWorks.Service.VoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class VoiceController {
    VoiceService voiceService;
    public VoiceController(VoiceService voiceService){
        this.voiceService = voiceService;
    }
    @PostMapping("/File")
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile file){
        return voiceService.uploadAudioFile(file);
    }

    @PostMapping("/Transcribe")
    public String transcribe(@RequestParam("file")MultipartFile file){
        return voiceService.convertAudioToText(file);
    }

    @PostMapping("/TTS")
    public boolean textToSpeech(@RequestBody String body){
        return voiceService.textToSpeech(body);
    }

}
