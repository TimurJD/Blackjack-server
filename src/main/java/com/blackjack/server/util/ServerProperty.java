package com.blackjack.server.util;

import java.io.IOException;
import java.util.Properties;

import static com.blackjack.server.constant.ErrorMessage.LOAD_PROPERTY_FAILD;

/**
 * The config properties holder for socket server.
 * It contains basic configuration like socket port, name.
 * Also config file should be dirrectly in classpath of the project like resources folder.
 *
 * @author Timur Berezhnoi
 */
public enum ServerProperty {

    SERVER_PORT("server.port"),
    SERVER_NAME("server.name");

    private final String key;

    ServerProperty(String key) {
        this.key = key;
    }

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(ServerProperty.class.getClassLoader().getResourceAsStream("server.properties"));
        } catch(IOException e) {
            throw new RuntimeException(LOAD_PROPERTY_FAILD.getMessage(), e);
        }
    }

    /**
     * The method returns property value or empty string
     * if value is not specified for an existing key,
     * the method can also return null if key is not specified in property file.
     *
     * @return property's value
     */
    public String getValue() {
        return properties.getProperty(key);
    }
}