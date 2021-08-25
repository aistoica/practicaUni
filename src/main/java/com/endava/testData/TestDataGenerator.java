package com.endava.testData;

import com.endava.models.Subject;
import com.endava.models.Teacher;
import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class TestDataGenerator {

	private Faker faker = new Faker();
	private DateFormat dateFormat = new SimpleDateFormat( "MM/dd/yyyy" );
	private DateFormat apiDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );

	public Teacher getTeacher() {
		Teacher teacher = new Teacher();
		teacher.setFirstName( faker.name().firstName() );
		teacher.setLastName( faker.name().lastName() );
		teacher.setBirthDate( dateFormat.format( faker.date().birthday() ) );
		teacher.setCnp( faker.number().digits( 13 ) );
		teacher.setSalary( faker.number().randomNumber() );
		teacher.setEmploymentDate( dateFormat.format( faker.date().past( 3 * 365, TimeUnit.DAYS ) ) );

		return teacher;
	}

	public Teacher getTeacherAPI() {
		Teacher teacher = new Teacher();
		teacher.setFirstName( faker.name().firstName() );
		teacher.setLastName( faker.name().lastName() );
		teacher.setBirthDate( apiDateFormat.format( faker.date().birthday() ) );
		teacher.setCnp( faker.number().digits( 13 ) );
		teacher.setSalary( faker.number().randomNumber() );
		teacher.setEmploymentDate( apiDateFormat.format( faker.date().past( 3 * 365, TimeUnit.DAYS ) ) );

		return teacher;
	}

	public Subject getSubject() {
		int percent = faker.number().numberBetween( 0, 100 );
		Subject subject = Subject.builder()
				.name( faker.educator().course() )
				.creditPoints( String.valueOf( faker.number().numberBetween( 1, 20 ) ) )
				.seminaryPercent( String.valueOf( percent ) )
				.coursePercent( String.valueOf( 100-percent ) )
				.optional( faker.random().nextBoolean() )
				.build();
		return subject;
	}

	public String getNumber( int minDigits, int maxDigits ) {
		return faker.number().digits( faker.number().numberBetween( minDigits, maxDigits ) );
	}

}
