/**
 * Implements a rain conditions embeddable entity object
 *
 * @Author Bandula Gamage
 */
package com.weather.demo.api.model.dto.openweathersvc;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RainEmbeddable {

    private Float oneHour;

    private Float threeHour;

    public RainEmbeddable() {
    }

    @JsonProperty("1h")
    public Float getOneHour() {
        return oneHour;
    }

    public void setOneHour(Float oneHour) {
        this.oneHour = oneHour;
    }

    @JsonProperty("3h")
    public Float getThreeHour() {
        return threeHour;
    }

    public void setThreeHour(Float threeHour) {
        this.threeHour = threeHour;
    }
}
