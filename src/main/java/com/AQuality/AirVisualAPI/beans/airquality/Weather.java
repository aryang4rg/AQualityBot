package com.AQuality.AirVisualAPI.beans.airquality;

import com.AQuality.AirVisualAPI.beans.Bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Weather implements Bean, Serializable {

    /**
     * Timestamp
     */
    private String ts;
    /**
     * temperature in celsius
     */
    private Integer tp;
    /**
     * atmospheric pressure in hPA
     */
    private Integer pr;
    /**
     * humidity %
     */
    private Integer hu;
    /**
     * wind speed
     */
    private Double ws;
    /**
     * wind direction
     */
    private Integer wd;
    /**
     * Icon corresponding to https://api-docs.iqair.com/?version=latest
     */
    private String ic;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

    public String getTs() {
        return ts;
    }

    public LocalDate getTime()
    {
        return LocalDate.parse(ts, formatter);
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Integer getTp() {
        return tp;
    }

    public Integer getTemperature()
    {
        return getTp();
    }

    public void setTp(Integer tp) {
        this.tp = tp;
    }

    public Integer getPr() {
        return pr;
    }

    public int getAtmosphericPressure() //hPA
    {
        return getPr();
    }

    public void setPr(Integer pr) {
        this.pr = pr;
    }

    public Integer getHu() {
        return hu;
    }

    public int getHumidity()
    {
        return getHu();
    }

    public void setHu(Integer hu) {
        this.hu = hu;
    }

    public Double getWs() {
        return ws;
    }
    public double getWindSpeed()
    {
        return getWs();
    }

    public void setWs(Double ws) {
        this.ws = ws;
    }

    public Integer getWd() {
        return wd;
    }

    public Integer getWindDirection()
    {
        return getWd();
    }

    public void setWd(Integer wd) {
        this.wd = wd;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

}