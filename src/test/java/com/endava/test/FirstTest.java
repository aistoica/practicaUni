package com.endava.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class FirstTest {

	@BeforeAll
	public static void beforeAll() {
		System.setProperty( "runTest", "false" );
		System.out.println( "Before All" );
	}

	@BeforeEach
	public void before() {
		System.out.println( "Before Each" );
	}

	@AfterAll
	public static void afterAll() {
		System.out.println( "After All" );
	}

	@AfterEach
	public void after() {
		System.out.println( "After Each" );
	}

	@Test
	@DisplayName( "This is a test" )
	//	@EnabledIfSystemProperty( named = "testSuite", matches = "smokeTest")
	@Tag( "adminUsage" )
	public void testMethod() {
		System.out.println( "Test Method" );
	}

	@Test
	@DisplayName( "This is a second test" )
	//	@EnabledIfSystemProperty( named = "testSuite", matches = "smokeTest")
	@Tag( "userUsage" )
	public void testMethod2() {
		System.out.println( "Test Method 2" );
	}

	@ParameterizedTest
	@MethodSource( "methodSource" )
	public void testMethod3( String name ) {
		System.out.println( name );
	}

	private static List<Arguments> methodSource() {
		List<Arguments> arguments = new ArrayList<>();
		arguments.add( Arguments.of( "SpiderMan" ) );
		arguments.add( Arguments.of( "WatchMen" ) );

		return arguments;
	}
}
