/**
 * Implements a cloud conditions embeddable entity object
 *
 * @Author Bandula Gamage
 */
package com.weather.demo.api.model.dto.openweathersvc;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CloudEmbeddable {

    private Integer all;

    public CloudEmbeddable() {
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
