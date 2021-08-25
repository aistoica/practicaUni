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

	public static String getBaseUri() {
		return properties.getProperty( "base.uri" );
	}

	public static Integer getPort() {
		return Integer.parseInt( properties.getProperty( "port" ) );
	}

	public static String getBasePath() {
		return properties.getProperty( "base.path" );
	}
}
