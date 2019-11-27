package org.jacocointegration.configuration;

public enum ConfigurationPropertyKey {
    JACOCO_BINARY_PATH("jacocoBinaryPath", "jacoco.exec"),
    JMX_RUNTIME_SERVER("jmxRuntimeServer", null),
    JMX_PORT("jmxPort", "5555");

    private final String key;
    private final String defaultValue;

    ConfigurationPropertyKey(String key, String value){
        this.key = key;
        this.defaultValue = value;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
