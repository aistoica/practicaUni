package com.endava.env;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {

	private static Properties properties = new Properties();

	static {
		InputStream resourceAsStream = EnvReader.class.getClassLoader().getResourceAsStream( "env/qa.properties" );
		try {
			properties.load( resourceAsStream );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public static String getUrl() {
		return properties.getProperty( "catalog.url" );
	}
}
