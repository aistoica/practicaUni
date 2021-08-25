package com.endava.client;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.RequestPrinter;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.util.HashSet;

public class LogFilter implements Filter {

	@Override
	public Response filter( FilterableRequestSpecification request, FilterableResponseSpecification responseSpecification, FilterContext context ) {

		RequestPrinter.print( request, request.getMethod(), request.getURI(), LogDetail.ALL, new HashSet<>(), System.out, true );
		Response response = context.next( request, responseSpecification );
		response.prettyPeek();

		return response;
	}
}
