package com.cooldatasoft.rest.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cooldatasoft.rest.data.SampleEntity;
import com.cooldatasoft.rest.service.SampleService;

@Named
@Path("/sample")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

	@Inject
	private SampleService sampleService;

	@POST
	@Path("/create")
	public Response create(SampleEntity entity) {
		return Response.ok(sampleService.create()).build();
	}

	@GET
	@Path("/read/{id}")
	public Response read(@PathParam("id") Long id) {
		return Response.ok(sampleService.read(id)).build();
	}

	@PUT
	@Path("/update")
	public Response update(SampleEntity entity) {
		return Response.ok(sampleService.update(entity)).build();
	}

	@DELETE
	@Path("/delete/{id}")
	public Response delete(@PathParam("id") Long id) {
		return Response.ok(sampleService.delete(id)).build();
	}

	@GET
	@Path("/list")
	public Response list(@QueryParam("startId") @DefaultValue("1") Long startId, @QueryParam("size") @DefaultValue("20") Integer size) {
		return Response.ok(sampleService.list(startId, size)).build();
	}
}
