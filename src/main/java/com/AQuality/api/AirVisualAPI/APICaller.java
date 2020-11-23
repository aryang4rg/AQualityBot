package com.AQuality.api.AirVisualAPI;

import com.AQuality.api.AirVisualAPI.beans.MainBean;
import com.AQuality.api.APIException;
import com.AQuality.core.Util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * calls the api for air visual
 * @param <T> the main bean for which it will transfer its given url contents to using the jackson library
 */
public abstract class APICaller<T extends MainBean>
{
    /**
     * url used to connect with and get its content
     */
    private URL url;
    /**
     * url connection of that url
     */
    private URLConnection connection;
    public static final String AIRVISUALAPIKEY = Util.AIRVISUALAPIKEY;
    ObjectMapper objectMapper = new ObjectMapper();
    /**
     * object of the main bean
     */
    T object;
    Class<? extends  T> clazz; //i actually do not know the use of this lmao

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
        String jsonString = Util.urlReader(connection);
        JsonNode node = objectMapper.readTree(jsonString);
        if (!node.path("status").asText().equalsIgnoreCase("success"))
        {
            throw new APIException(node.path("data").path("message").asText());
        }
        object = objectMapper.readValue(jsonString, clazz);
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
