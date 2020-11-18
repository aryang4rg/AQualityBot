package com.AQuality.api.AirVisualAPI.beans.airquality;

import com.AQuality.api.AirVisualAPI.beans.MainBean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Weather implements Serializable {

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

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Integer getTp() {
        return tp;
    }

    public Integer getTemperatureC()
    {
        return getTp();
    }

    public Integer getTemperatureF()
    {
        return (int)(getTp()*1.8) + 32;
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

    public String getWeatherInPhotoForm()
    {
        return "https://www.airvisual.com/images/" + getIc() + ".png";
    }

    public LocalDateTime getTime()
    {
        return LocalDateTime.parse(getTs(), MainBean.formatter);
    }

    public String getWeatherInDescriptionForm()
    {
        switch (getIc())
        {
            case ("01d"):
            {
                return "clear sky (day)";
            }
            case ("01n"):
            {
                return "clear sky (night)";
            }
            case ("02d"):
            {
                return "few clouds (day)";
            }
            case ("02n"):
            {
                return "few clouds (night)";
            }
            case ("03d"):
            {
                return "scattered clouds";
            }
            case ("04d"):
            {
                return "broken clouds";
            }
            case ("09d"):
            {
                return "shower rain";
            }
            case ("10d"):
            {
                return "rain (day)";
            }
            case ("10n"):
            {
                return "rain (night)";
            }
            case ("11d"):
            {
                return "thunderstorm";
            }
            case ("13d"):
            {
                return "snow";
            }
            case ("50d"):
            {
                return "mist";
            }
        }
        return null;
    }

    public String getWeatherInEmojiForm()
    {
        switch (getIc())
        {
            case ("01d"):
            {
                return "☀";
            }
            case ("01n"):
            {
                return "\uD83C\uDF19";
            }
            case ("02d"):
            case ("02n"):
            case ("03d"):
            case ("04d"):
            {
                return "☁";
            }

            case ("09d"):
            case ("10d"):
            case ("10n"): {
                return "\uD83C\uDF27";
            }
            case ("11d"):
            {
                return "\uD83C\uDF29";
            }
            case ("13d"):
            {
                return "\uD83C\uDF28";
            }
            case ("50d"):
            {
                return "\uD83C\uDF2B";
            }
        }
        return null;
    }

}