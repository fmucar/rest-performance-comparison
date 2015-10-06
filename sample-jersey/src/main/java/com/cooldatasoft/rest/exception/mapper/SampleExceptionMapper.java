package com.cooldatasoft.rest.exception.mapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SampleExceptionMapper implements ExceptionMapper<WebApplicationException>{

	@Override
	public Response toResponse(WebApplicationException exception) {
		return Response.serverError().entity(exception).build();
	}

}
