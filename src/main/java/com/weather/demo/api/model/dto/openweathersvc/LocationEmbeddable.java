package com.weather.demo.api.model.dto.openweathersvc;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LocationEmbeddable {

    private Long lon;

    private Long lat;

    public LocationEmbeddable() {
    }

    public Long getLon() {
        return lon;
    }

    public void setLon(Long lon) {
        this.lon = lon;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "LocationEmbeddable{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
