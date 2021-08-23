package com.endava.testData;

import com.endava.models.Teacher;
import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class TestDataGenerator {

	private Faker faker = new Faker();
	private DateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy" );

	public Teacher getTeacher() {
		Teacher teacher = new Teacher();
		teacher.setFirstName( faker.name().firstName() );
		teacher.setLastName( faker.name().lastName() );
		teacher.setBirthDate( dateFormat.format( faker.date().birthday() ) );
		teacher.setCnp( faker.number().digits( 13 ) );
		teacher.setSalary( String.valueOf( faker.number().randomNumber() ) );
		teacher.setEmploymentDate( dateFormat.format( faker.date().past( 3 * 365, TimeUnit.DAYS ) ) );

		return teacher;
	}

	public String getNumber(int minDigits, int maxDigits) {
		return faker.number().digits( faker.number().numberBetween( minDigits, maxDigits ) );
	}

}
