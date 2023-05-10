package com.example.VWorks.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@Service
public class DataService {
    @Autowired
    private ResourceLoader resourceLoader;
    private HashMap<String, JSONObject> countryHashMap = new HashMap<>();
    private HashMap<String, JSONObject> countryHashMapByCode = new HashMap<>();
    private String jsonFileData = "";


    @PostConstruct
    private void init(){
        Resource resource = resourceLoader.getResource("classpath:files/countryCodes.txt");
        try {
            InputStream inputStream = resource.getInputStream();
            jsonFileData = readFile(inputStream);
            JSONArray countryArray = new JSONArray(jsonFileData);

            for (int x = 0; x < countryArray.length(); x++){
                JSONObject jsonObject = countryArray.getJSONObject(x);
                countryHashMap.put(jsonObject.getString("name"), jsonObject);
                countryHashMapByCode.put(jsonObject.getString("dial_code"), jsonObject);
            }
        } catch (IOException e) {
            System.out.println("Exception in init: "+e.getMessage());
        }
    }

    public ResponseEntity getWelcomeMsg(){
        return new ResponseEntity("Welcome!", HttpStatus.OK);
    }

    public ResponseEntity getEmoji(){
        return new ResponseEntity("\ud83d\udc4d", HttpStatus.OK);
    }

    public ResponseEntity getCountryCodes(){
        return ResponseEntity.ok(jsonFileData.toString());
    }

    public ResponseEntity findCountryByName(String countryName){
        try {

            if(countryHashMap.containsKey(countryName)){
                return ResponseEntity.ok(countryHashMap.get(countryName).toString());
            }else{
                return ResponseEntity.badRequest().body(String.format("Country %s Not Found",countryName));
            }
        } catch (Exception e) {
            // handle exception
            return ResponseEntity.badRequest().body("Exception in getCountryCodes: "+e.getMessage());
        }
    }

    public ResponseEntity findCountryByCode(String countryName){
        try {
            if(countryHashMapByCode.containsKey(countryName)){
                return ResponseEntity.ok(countryHashMapByCode.get(countryName).toString());
            }else{
                return ResponseEntity.badRequest().body(String.format("Country Code %s Not Found",countryName));
            }
        } catch (Exception e) {
            // handle exception
            return ResponseEntity.badRequest().body("Exception in getCountryCodes: "+e.getMessage());
        }
    }

    private String readFile(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // build a string of the file data using string builder as string are immutable
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            // handle exception
            System.out.println("Error in readFile :"+e.getMessage());
            return ("{\"Error\" :\""+e.getMessage()+"\"");
        }
    }
}

