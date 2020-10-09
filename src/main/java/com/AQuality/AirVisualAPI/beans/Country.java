package com.AQuality.AirVisualAPI.beans;

import java.io.Serializable;

public class Country implements Serializable, Bean {

    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}