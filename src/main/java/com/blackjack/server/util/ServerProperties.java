package com.blackjack.server.util;

import java.util.Properties;

/**
 * @author Timur Berezhnoi
 */
public enum ServerProperties {

	PORT("server.port"),
	NAME("server.name");

	private String key;

	private ServerProperties(String key) {
		this.key = key;
	}

	private static Properties properties;

	static {
		properties = new Properties();
		try {
			properties.load(ServerProperties.class.getClassLoader().getResourceAsStream("server.properties"));
		} catch (Exception e) {
			throw new RuntimeException("Error when loading configuration file", e);
		}
	}

	/**
	 * Call the <tt>Properties</tt> method {@code get}
	 * to get data by key provided in the current enum value. 
	 * @return value by key
	 */
	public Object getValue() {
		return properties.get(key);
	}
}