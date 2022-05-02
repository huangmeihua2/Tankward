package com.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyConfig {

    private static PropertyConfig propertyConfig;
    private Properties properties = new Properties();
    private PropertyConfig() {
        try {
            properties.load(getClass().getResourceAsStream("/Config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyConfig getPropertyConfig(){
        if(propertyConfig==null){
            synchronized (PropertyConfig.class){
                if(propertyConfig==null) propertyConfig = new PropertyConfig();
            }
        }
        return propertyConfig;
    }
    public Object getValue(String key){
        return properties.getProperty(key);
    }
    public static void main(String[] args) {
        PropertyConfig propertyConfig = PropertyConfig.getPropertyConfig();
        System.out.println(propertyConfig.properties.getProperty("intiallTankCount"));
    }
}
