package com.example.VWorks.Service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.HashMap;


@Service
public class WebToolsService {

    public String scalpWebsite(String body) {
        JSONObject jsonObject = new JSONObject(body);
        System.out.println(jsonObject.toString(4));

        return jsonObject.toString(4);
    }

    private HashMap buildSiteMap() {
        HashMap map = new HashMap();
        for (int x = 0; x < 10; x++){
            map.put(x,"www.website"+x+".ie");
        }
        return map;
    }
}
