package io.aelite.ledwall.core.property;

import io.aelite.ledwall.core.LedWallApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {

    private static final Logger logger = LoggerFactory.getLogger(PropertyManager.class);
    private Map<String, String> defaultProperties;
    private Properties properties;

    public PropertyManager() {
        this.defaultProperties = new HashMap<>();
        this.properties = new Properties();

        this.loadProperties();
    }

    public void loadProperties(){
        try {
            //TODO load application properties from beside jar file, not from resources
            this.properties.load(LedWallApplication.class.getResourceAsStream("/application.properties"));
        } catch (Exception e) {
            logger.error("Failed to load application properties", e);
        }
    }

    public void setDefault(String key, String value){
        this.defaultProperties.put(key, value);
    }

    public void setDefault(String key, boolean value){
        this.setDefault(key, String.valueOf(value));
    }

    public void setDefault(String key, int value){
        this.setDefault(key, String.valueOf(value));
    }

    public void setDefault(String key, double value){
        this.setDefault(key, String.valueOf(value));
    }

    public String getString(String key){
        String prop = this.properties.getProperty(key);

        if (prop != null){
            return prop;
        }

        return this.defaultProperties.get(key);
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(this.getString(key));
    }

    public int getInt(String key) {
        return Integer.parseInt(this.getString(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(this.getString(key));
    }
}
