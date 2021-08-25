package com.endava.test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import com.endava.client.TeacherClient;
import com.endava.env.EnvReader;
import com.endava.models.Teacher;
import com.endava.testData.TestDataGenerator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestTest {

	private TestDataGenerator dataGenerator = new TestDataGenerator();
	private TeacherClient teacherClient = new TeacherClient();

	@Test
	public void createTeacher() {

		//GIVEN
		Teacher teacher = dataGenerator.getTeacherAPI();

		//WHEN
		Response response = teacherClient.createTeacher( teacher );

		//THEN
		response.then()
				.statusCode( HttpStatus.SC_OK )
				.header( "Content-Type", is( "application/json" ) )
				.body( "id", both( notNullValue() ).and( instanceOf( Number.class ) ) )
				.body( "firstName", is( teacher.getFirstName() ) )
				.body( "lastName", is( teacher.getLastName() ) )
				.body( "subjects", nullValue() )
				.body( "employmentDate", is( teacher.getEmploymentDate() ) )
				.body( "birthDate", is( teacher.getBirthDate() ) )
				.body( "cnp", is( teacher.getCnp() ) )
				.body( "salary", is( teacher.getSalary().intValue() ) );
	}

	@Test
	public void getTeacherById() {
		//GIVEN
		Teacher teacher = dataGenerator.getTeacherAPI();
		Response createTeacherResponse = teacherClient.createTeacher( teacher );
		createTeacherResponse.then().statusCode( HttpStatus.SC_OK );
		int id = createTeacherResponse.jsonPath().getInt( "id" );

		//WHEN
		Response teacherByIdResponse = teacherClient.getTeacherById( id );

		//THEN
		teacherByIdResponse.then()
				.statusCode( HttpStatus.SC_OK )
				.header( "Content-Type", is( "application/json" ) )
				.body( "id", is( id ) )
				.body( "firstName", is( teacher.getFirstName() ) )
				.body( "lastName", is( teacher.getLastName() ) )
				.body( "subjects", hasSize( 0 ) )
				.body( "employmentDate", is( teacher.getEmploymentDate() ) )
				.body( "birthDate", is( teacher.getBirthDate() ) )
				.body( "cnp", is( teacher.getCnp() ) )
				.body( "salary", is( teacher.getSalary().intValue() ) );
	}

	@Test
	public void shouldGetFilterList() {
		//GIVEN
		Teacher teacher = dataGenerator.getTeacherAPI();
		Response createTeacherResponse = teacherClient.createTeacher( teacher );
		createTeacherResponse.then().statusCode( HttpStatus.SC_OK );
		int id = createTeacherResponse.jsonPath().getInt( "id" );

		//WHEN
		Response filterTeacherResponse = teacherClient.filterTeacherList( teacher.getFirstName(), null, null );

		//THEN
		filterTeacherResponse
				.then()
				.statusCode( HttpStatus.SC_OK )
				.body( "size()", greaterThanOrEqualTo( 1 ) )
				.body( "firstName", everyItem( is( teacher.getFirstName() ) ) )
				.body( "find{ t -> t.id == %s }.lastName", withArgs( id ), is( teacher.getLastName() ) )
				.body( "find{ t -> t.id == %s }.birthDate", withArgs( id ), is( teacher.getBirthDate() ) )
				.body( "find{ t -> t.id == %s }.cnp", withArgs( id ), is( teacher.getCnp() ) )
				.body( "find{ t -> t.id == %s }.salary", withArgs( id ), is( teacher.getSalary().intValue() ) )
				.body( "find{ t -> t.id == %s }.employmentDate", withArgs( id ), is( teacher.getEmploymentDate() ) );
	}

	@Test
	public void getTeacherByIdGivenTeacherWasEdited() {
		//GIVEN
		Teacher teacher = dataGenerator.getTeacherAPI();
		Response createTeacherResponse = teacherClient.createTeacher( teacher );
		createTeacherResponse.then().statusCode( HttpStatus.SC_OK );
		int id = createTeacherResponse.jsonPath().getInt( "id" );

		Teacher newTeacher = dataGenerator.getTeacherAPI();
		Response editTeacherResponse = teacherClient.editTeacher( id, newTeacher );
		editTeacherResponse.then().statusCode( HttpStatus.SC_OK );

		//WHEN
		Response teacherByIdResponse = teacherClient.getTeacherById( id );

		//THEN
		teacherByIdResponse.then()
				.statusCode( HttpStatus.SC_OK )
				.header( "Content-Type", is( "application/json" ) )
				.body( "id", is( id ) )
				.body( "firstName", is( newTeacher.getFirstName() ) )
				.body( "lastName", is( newTeacher.getLastName() ) )
				.body( "subjects", hasSize( 0 ) )
				.body( "employmentDate", is( newTeacher.getEmploymentDate() ) )
				.body( "birthDate", is( newTeacher.getBirthDate() ) )
				.body( "cnp", is( newTeacher.getCnp() ) )
				.body( "salary", is( newTeacher.getSalary().intValue() ) );
	}

	@Test
	public void shouldFailToGetTeacherByIdGivenTeacherWasDeleted() {
		//GIVEN
		Teacher teacher = dataGenerator.getTeacherAPI();
		Response createTeacherResponse = teacherClient.createTeacher( teacher );
		createTeacherResponse.then().statusCode( HttpStatus.SC_OK );
		int id = createTeacherResponse.jsonPath().getInt( "id" );

		Response deleteTeacherResponse = teacherClient.deleteTeacher( id );
		deleteTeacherResponse.then().statusCode( HttpStatus.SC_OK );

		//WHEN
		Response teacherByIdResponse = teacherClient.getTeacherById( id );

		//THEN
		teacherByIdResponse.then()
				.statusCode( HttpStatus.SC_NOT_FOUND )
				.body( "errorCode", is( "teacher.not.found" ) )
				.body( "message", is( "Teacher not found" ) )
				.body( "status", is( HttpStatus.SC_NOT_FOUND ) );
	}

}
