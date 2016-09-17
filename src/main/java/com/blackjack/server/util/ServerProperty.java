package com.blackjack.server.util;

import com.blackjack.server.constant.ErrorMessage;

import java.io.IOException;
import java.util.Properties;

import static com.blackjack.server.constant.ErrorMessage.LOAD_PROPERTY_FAILD;

/**
 * @author Timur Berezhnoi
 */
public enum ServerProperty {

	PORT("server.port"),
	NAME("server.name");
	
	private String key;

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
	 * Call the <tt>Properties</tt> method {@code get}
	 * to get data by key provided in the current enum value. 
	 * @return value by key
	 */
	public String getValue() {
		return properties.getProperty(key);
	}
}