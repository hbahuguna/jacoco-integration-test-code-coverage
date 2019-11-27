package org.jacocointegration.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;

public class ConfigurationProperties {
    private static Map<String, String> propertiesMap = new HashMap<>();

    /**
     * Places all environment variables and system properties into a properties map
     */
    public ConfigurationProperties() {
        Map<String, String> environmentMap = System.getenv();
        for (String key : environmentMap.keySet()) {
            propertiesMap.put(key, environmentMap.get(key));
        }
        Properties systemProperties = System.getProperties();
        for (String key : systemProperties.stringPropertyNames()) {
            propertiesMap.put(key, systemProperties.getProperty(key));
        }
    }


    /**
     * Gets a property based on a key or a default value
     *
     * @param configurationPropertyKey
     * @return the property of the key or the default value
     */
    public String getProperty(ConfigurationPropertyKey configurationPropertyKey) {
        return defaultIfEmpty(propertiesMap.get(configurationPropertyKey.getKey()), configurationPropertyKey.getDefaultValue());
    }

    public void setProperty(String key, String value) {
        propertiesMap.put(key, value);
    }
}
