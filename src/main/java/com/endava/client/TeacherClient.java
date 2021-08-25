package com.endava.client;

import static io.restassured.RestAssured.given;

import com.endava.env.EnvReader;
import com.endava.models.Teacher;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TeacherClient {

	public Response createTeacher( Teacher teacher ) {
		return given().baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.filter( new LogFilter() )
				.body( teacher )
				.contentType( ContentType.JSON )
				.post("teacher");
	}

	public Response getTeacherById(int id) {
		return given().baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.filter( new LogFilter() )
				.pathParam( "id", id )
				.get("/teacher/{id}");
	}

	public Response filterTeacherList(String firstName, String lastName, String cnp) {
		RequestSpecification request = given().baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.filter( new LogFilter() );
		if(firstName != null) {
			request.queryParam( "firstName", firstName );
		}
		if(lastName != null) {
			request.queryParam( "lastName", lastName );
		}
		if(cnp != null) {
			request.queryParam( "cnp", cnp );
		}
		return request.get("/teacher/filter");
	}

	public Response editTeacher(int id, Teacher teacher) {
		return given().baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.filter( new LogFilter() )
				.body( teacher )
				.contentType( ContentType.JSON )
				.pathParam( "id", id )
				.put("/teacher/{id}");
	}

	public Response deleteTeacher(int id) {
		return given().baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() )
				.filter( new LogFilter() )
				.pathParam( "id", id )
				.delete("/teacher/{id}");
	}
}
