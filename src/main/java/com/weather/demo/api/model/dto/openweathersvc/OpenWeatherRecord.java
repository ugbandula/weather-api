/**
 * Implements a Weather Status entity object
 *
 * @Author Bandula Gamage
 */
package com.weather.demo.api.model.dto.openweathersvc;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class OpenWeatherRecord {

    // City Id
    private Long id;

    // Base
    private String base;

    // Visibility
    private Integer visibility;

    // Last updated / Time of data calculation
    private Long dt;

    // Time zone
    private Integer timezone;

    // City name
    private String name;

    // cod
    private Integer cod;

    @Embedded
    private LocationEmbeddable coord;

    private List<Weather> weather;

    @Embedded
    private MainEmbeddable main;

    @Embedded
    private WindEmbeddable wind;

    @Embedded
    private RainEmbeddable rain;

    @Embedded
    private SnowEmbeddable snow;

    @Embedded
    private CloudEmbeddable clouds;

    @Embedded
    private SysEmbeddable sys;

    public OpenWeatherRecord() {
        super();
        this.weather = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public LocationEmbeddable getCoord() {
        return coord;
    }

    public void setCoord(LocationEmbeddable coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public MainEmbeddable getMain() {
        return main;
    }

    public void setMain(MainEmbeddable main) {
        this.main = main;
    }

    public WindEmbeddable getWind() {
        return wind;
    }

    public void setWind(WindEmbeddable wind) {
        this.wind = wind;
    }

    public CloudEmbeddable getClouds() {
        return clouds;
    }

    public void setClouds(CloudEmbeddable clouds) {
        this.clouds = clouds;
    }

    public SysEmbeddable getSys() {
        return sys;
    }

    public void setSys(SysEmbeddable sys) {
        this.sys = sys;
    }

    public RainEmbeddable getRain() {
        return rain;
    }

    public void setRain(RainEmbeddable rain) {
        this.rain = rain;
    }

    public SnowEmbeddable getSnow() {
        return snow;
    }

    public void setSnow(SnowEmbeddable snow) {
        this.snow = snow;
    }

    @Override
    public String toString() {
        return "CityWeatherStatus{" +
                "id=" + id +
                ", base='" + base + '\'' +
                ", visibility=" + visibility +
                ", dt=" + dt +
                ", timezone=" + timezone +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", coord=" + coord +
                ", weather=" + weather +
                ", main=" + main +
                ", wind=" + wind +
                ", rain=" + rain +
                ", snow=" + snow +
                ", clouds=" + clouds +
                ", sys=" + sys +
                '}';
    }
}
