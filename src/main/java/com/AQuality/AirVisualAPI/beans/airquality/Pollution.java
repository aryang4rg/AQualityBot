package com.AQuality.AirVisualAPI.beans.airquality;

import com.AQuality.AirVisualAPI.beans.Bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Pollution implements Bean, Serializable {

    /**
     * timestamp
     */
    private String ts;
    /**
     * number of pollutant in AQI scale
     */
    private Integer aqius;
    /**
     * main pollutant
     */
    private String mainus;
    private Integer aqicn; //chinese air quality stuff, irrelevant
    private String maincn;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);


    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public LocalDate getTime()
    {
        return LocalDate.parse(ts, formatter);
    }

    public Integer getAqius() {
        return aqius;
    }

    public Integer getAqi() {
        return getAqius();
    }


    public void setAqius(Integer aqius) {
        this.aqius = aqius;
    }

    public String getMainus() {
        return mainus;
    }

    public String getMainPollutant()
    {
        return getMainus();
    }
    public void setMainus(String mainus) {
        this.mainus = mainus;
    }

    public Integer getAqicn() {
        return aqicn;
    }

    public void setAqicn(Integer aqicn) {
        this.aqicn = aqicn;
    }

    public String getMaincn() {
        return maincn;
    }

    public void setMaincn(String maincn) {
        this.maincn = maincn;
    }

}