package ru.geekbrains.parser.avito;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.model.Task;

@Service
@Log
public class AvitoParser {


    @Autowired
    private AvitoClient client;

    public String parse(Task task) {

        try {
            Integer cityId = this.findCityId(task.getCity());
            String prefix = this.findCityPrefix(cityId);



            return client.apartmentList(prefix, 104, 1);
        } catch (CityNotFound e) {
            log.info(e.getMessage());
            return null;
        }
    }

    private Integer findCityId(String city) throws CityNotFound {

        String response = client.findCity(city);

        if (response == null) {
            throw new CityNotFound("Empty city list");
        }

        JsonObject object = new Gson().fromJson(response, JsonObject.class);
        JsonArray locations = object.get("result").getAsJsonObject().get("locations").getAsJsonArray();

        for (JsonElement location : locations) {
            JsonObject locationObject = location.getAsJsonObject();
            Integer cityId = locationObject.get("id").getAsInt();
            String name = locationObject.get("names").getAsJsonObject().get("1").getAsString();
            if (name.equalsIgnoreCase(city)) {
                return cityId;
            }
        }

        throw new CityNotFound("City not found");
    }

    private String findCityPrefix(Integer cityId) throws CityNotFound {

        String response = client.cityInfo(cityId);

        if (response == null) {
            throw new CityNotFound("Empty city location");
        }

        JsonObject object = new Gson().fromJson(response, JsonObject.class);

        return object.get("url").getAsString();
    }



}
