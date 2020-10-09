package com.AQuality.AirVisualAPI.beans;

import java.io.Serializable;
import java.util.List;

public class Countries extends MainBean implements Serializable {

    private String status;
    private List<Country> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Country> getData() {
        return data;
    }

    public void setData(List<Country> data) {
        this.data = data;
    }

}
