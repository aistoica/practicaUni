package com.endava.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class SecondTest {

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
		int r = 1+2;
		assertEquals(3, r);
		assertTrue( 4 == r );
	}

	@Test
	@DisplayName( "This is a second test" )
	//	@EnabledIfSystemProperty( named = "testSuite", matches = "smokeTest")
	@Tag( "userUsage" )
	public void testMethod2() {
		System.out.println( "Test Method 2" );
		int r = 2+3;// anotherClass.getMathodWithParms
		assertThat(r).isEqualTo( 5 )
				.isBetween( 3,5 )
				.isPositive()
				.isEven();
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
