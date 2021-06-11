package com.example.patatapp.ui.ws;

import com.example.patatapp.bo.Potager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CityWS {

    @PostMapping("/ws/cities")
    public List<String> getCities(RestTemplate restTemplate, Potager potager) {
        List<String> cities = new ArrayList<>();
        String json;
        String url = "http://api.zippopotam.us/fr/" + potager.getZipCode();
        try {
            json = restTemplate.getForObject(url , String.class);
        } catch (Exception e) {
            cities.add("error");
            return cities;
        }
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("places");
        jsonArray.iterator().forEachRemaining(o -> cities.add(((JSONObject) o).getString("place name")));
        return cities;
    }

}
