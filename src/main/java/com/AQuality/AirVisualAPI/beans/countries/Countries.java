package com.AQuality.AirVisualAPI.beans.countries;

import com.AQuality.AirVisualAPI.beans.Bean;
import com.AQuality.AirVisualAPI.beans.MainBean;

import java.io.Serializable;
import java.util.List;

public class Countries implements Serializable, Bean, MainBean {

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
