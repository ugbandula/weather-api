/**
 * Implements a service which communicates with the OpenWeatherMapService.
 *
 * @Author Bandula Gamage
 */

package com.weather.demo.api.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.demo.api.model.dto.openweathersvc.OpenWeatherRecord;
import com.weather.demo.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class OpenWeatherMapService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapService.class);

    // Last read date and time
    private long lastReadTime;

    public OpenWeatherRecord findWeatherForTheCity(String country, String city) throws Exception {
        OpenWeatherRecord status = null;

        return readFromOpenWeatherService(country, city);
    }

    /**
     * Reads from the OpenWeatherMapService for a given country and a city.
     * @param country Country
     * @param city City
     * @return CityWeatherStatus object or throws an exception if any error comes across.
     */
    private OpenWeatherRecord readFromOpenWeatherService(String country, String city) {
        try {
            String fetchUrl = generateUrl(country, city);
            LOGGER.info("[OpenWeatherMapService.readData] Fetching data from {}", fetchUrl);

            URL url = new URL(fetchUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", MediaType.APPLICATION_JSON);

            // Get res/access mapping as json
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8.name()))) {
                // Build app res access map from json response
                // Read json and write to db
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<OpenWeatherRecord> typeReference = new TypeReference<OpenWeatherRecord>(){};

                try {
                    OpenWeatherRecord weatherData = mapper.readValue(br,typeReference);
                    if (weatherData == null)
//                        LOGGER.info(weatherData.toString());
//                    else
                        LOGGER.info("[OpenWeatherMapService] Weather data read failed");

                    lastReadTime = System.currentTimeMillis();

                    return weatherData;
                } catch (Exception e){
                    e.printStackTrace();
                    LOGGER.info("[OpenWeatherMapService] Unable to read from weather service:", e.getMessage());

                    throw new IOException("Unable to read from weather service");
                }
            }
        } catch (Exception e) {
            LOGGER.info("[OpenWeatherMapService] Unable to read from weather service. Reason: " + e.getMessage());
        }

        return null;
    }

    /**
     * Generates the data read url with the given parameters.
     * @param country Country
     * @param city City
     * @return URL string
     */
    private String generateUrl(String country, String city) {
        StringBuffer fetchUrl = new StringBuffer(Constants.OPEN_WEATHER_MAP_API_BASE);
        fetchUrl.append(city);
        fetchUrl.append(",");
        fetchUrl.append(country);
        fetchUrl.append("&appid=");
        fetchUrl.append(Constants.OPEN_WEATHER_MAP_API_KEY);
        return fetchUrl.toString();
    }
}
