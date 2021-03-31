package com.weather.demo;

import com.weather.demo.api.controller.WeatherController;
import com.weather.demo.api.exception.BadRequestException;
import com.weather.demo.api.model.dto.CityWeatherStatusDto;
import com.weather.demo.api.service.WeatherService;
import com.weather.demo.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = { ApplicationTests.class })
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private WeatherService weatherService;

	@MockBean
	private WeatherController weatherController;

	@Test
	void contextLoads() {
		assertThat(weatherService).isNotNull();
		assertThat(weatherController).isNotNull();
	}

	@Test
	public void weatherForecastTest() throws Exception {
		String country = "AU";
		String city = "Sydney";
		String invalidCity = "Sy";
		String apiKeyValue = Constants.SAMPLE_API_KEYs[0];
		Optional<String> apiKey = Optional.of(apiKeyValue);

		/**
		 * Checks whether the correct input and output delivers correct result
		 */
		CityWeatherStatusDto weatherObj = weatherService.findWeatherForCity(country, city);
		assertThat(weatherObj).isNotNull();
		assertThat(weatherObj.getDescription()).isNotNull();
		assertEquals(true, weatherObj.getCountry().equals(country));
		assertEquals(true, weatherObj.getCity().equalsIgnoreCase(city));

		/**
		 * Checks the returning Exceptions due to improper inputs
		 */
		assertThat(weatherController.getWeatherForTheCity(country, city, null, apiKey)).isNotNull();
//		assertThat(weatherController.getWeatherForTheCity(country, invalidCity, null, apiKey)).isNotNull();

		assertThrows(BadRequestException.class, () -> {
			weatherController.getWeatherForTheCity(country, invalidCity, null, apiKey);
		});
	}
}
