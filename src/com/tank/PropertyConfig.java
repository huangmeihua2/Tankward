package com.tank;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class PropertyConfig implements Serializable {
    private static PropertyConfig propertyConfig;
    private Properties properties = new Properties();
    private Properties getColliderConfigProperties = new Properties();
    private PropertyConfig() {
        try {
            properties.load(getClass().getResourceAsStream("/Config"));
            getColliderConfigProperties.load(getClass().getResourceAsStream("/ColliderConfig"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyConfig getPropertyConfig() {
        if (propertyConfig == null) {
            synchronized (PropertyConfig.class) {
                if (propertyConfig == null) propertyConfig = new PropertyConfig();
            }
        }
        return propertyConfig;
    }
    public Object[] getColliderConfig(){
        return getColliderConfigProperties.values().toArray();
    }
    public Object getValue(String key) {
        return properties.getProperty(key);
    }
}
