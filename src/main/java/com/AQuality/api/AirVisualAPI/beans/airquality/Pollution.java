package com.AQuality.api.AirVisualAPI.beans.airquality;

import com.AQuality.api.AirVisualAPI.beans.MainBean;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Pollution implements Serializable {

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


    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }


    public Integer getAqius() {
        return aqius;
    }

    public Integer getAqi() {
        return getAqius();
    }

    public String getDescriptionOfAqi()
    {
        if (aqius <= 50)
        {
            return "Air quality is satisfactory, and air pollution poses little or no risk";
        }
        else if(aqius <= 100)
        {
            return "Air quality is acceptable. However, there may be a risk for some people, particularly " +
                    "those who are unusually sensitive to air pollution.";
        }
        else if(aqius <= 150)
        {
            return "Members of sensitive groups may experience health effects. The general public is less likely to be affected.";
        }
        else if(aqius <= 200)
        {
            return "Some members of the general public may experience health effects; members" +
                    "of sensitive groups may experience more serious health effects.";
        }
        else if(aqius <= 300)
        {
            return "Health alert: The risk of health effects is increased for everyone.";
        }
        else
        {
            return "Health alert: The risk of health effects is increased for everyone.";
        }
    }

    public Color getColorOfAqi()
    {
        if (aqius <= 50)
        {
            return Color.green;
        }
        else if(aqius <= 100)
        {
            return Color.YELLOW;
        }
        else if(aqius <= 150)
        {
            return new Color(255, 126, 0);
        }
        else if(aqius <= 200)
        {
            return Color.RED;
        }
        else if(aqius <= 300)
        {
            return new Color(143, 63, 151);
        }
        else
        {
            return new Color(126,0,35);
        }
    }

    public String getFullNameOfPollutant()
    {
        switch (getMainPollutant())
        {
            case p2:
            {
                return "Particles < 2.5 μm";
            }
            case p1:
            {
                return "Particles < 10 μm";
            }
            case o3:
            {
                return "Ozone O₃";
            }
            case n2:
            {
                return "Nitrogen Dioxide NO₂";
            }
            case s2:
            {
                return "Sulfur Dioxide SO₂";
            }
            case co:
            {
                return "Carbon Monoxide CO";
            }
        }

        return null;
    }

    public LocalDateTime getTime()
    {
        return LocalDateTime.parse(getTs(), MainBean.formatter);
    }

    public void setAqius(Integer aqius) {
        this.aqius = aqius;
    }

    public String getMainus() {
        return mainus;
    }

    public Pollutants getMainPollutant()
    {
        return Enum.valueOf(Pollutants.class, getMainus());
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