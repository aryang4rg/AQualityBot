package com.AQuality.AirVisualAPI;

import com.AQuality.AirVisualAPI.beans.MainBean;
import com.AQuality.core.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public abstract class APICaller<T extends MainBean>
{
    private URL url;
    private URLConnection connection;
    public static final String AIRVISUALAPIKEY = Util.AIRVISUALAPIKEY;
    ObjectMapper objectMapper = new ObjectMapper();
    T object;
    Class<? extends  T> clazz;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public APICaller(URL url, Class<? extends T> clazz) throws Exception
    {
        this.clazz = clazz;
        this.url = url;
        connection = url.openConnection();
        object = objectMapper.readValue(urlReader(), clazz);
        if (!object.getStatus().equalsIgnoreCase("success"))
        {
            throw new APIException("Error: " + object.getStatus());
        }
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URLConnection getConnection() {
        return connection;
    }

    public void setConnection(URLConnection connection) {
        this.connection = connection;
    }

    public String urlReader() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        StringBuilder allTokens = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
        {
            allTokens.append(inputLine);
        }
        in.close();
        return allTokens.toString();
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(object);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return clazz.toString();
    }
}
