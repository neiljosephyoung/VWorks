package com.example.VWorks.Service;


import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;


@Service
public class VoiceService {

    public ResponseEntity uploadAudioFile(MultipartFile file) {
        try {
            if (file != null) {
                System.out.println("Inbound file content : " + new String(file.getBytes(), StandardCharsets.UTF_8));
                return ResponseEntity.ok("File uploaded successfully");
            } else {
                return ResponseEntity.badRequest().body("File upload failed");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertAudioToText(MultipartFile file) {
        try {
            Configuration configuration = new Configuration();

            configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

            StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
            //InputStream stream = getClass().getResourceAsStream("/audio/thisIsaTest.wav");
            InputStream stream = file.getInputStream();

            recognizer.startRecognition(stream);
            SpeechResult result;
            StringBuilder resultString = new StringBuilder();
            while ((result = recognizer.getResult()) != null) {
                String hypo = "";
                hypo = result.getHypothesis();
                System.out.format("Hypothesis: %s\n", hypo);
                resultString.append(hypo);
            }
            recognizer.stopRecognition();
            return resultString.toString();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public String transcribe(MultipartFile file) {
        return TranscriberDemo.transcribe(file);
    }

    public boolean textToSpeech(String body) {
        RestTemplate restTemplate = new RestTemplate();
        String ttsEndpoint = "http://localhost:8000/tts";
        String requestBody = body;
        restTemplate.postForObject(ttsEndpoint, requestBody, String.class);
        return true;
    }
}