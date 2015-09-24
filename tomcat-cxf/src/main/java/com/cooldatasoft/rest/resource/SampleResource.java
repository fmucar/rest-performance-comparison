package com.cooldatasoft.rest.resource;

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

@Path("/sample")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SampleResource {

	@POST
	@Path("/create")
	public Response create(SampleEntity entity);

	@GET
	@Path("/read/{id}")
	public Response read(@PathParam("id") Long id);

	@PUT
	@Path("/update")
	public Response update(SampleEntity entity);

	@DELETE
	@Path("/delete/{id}")
	public Response delete(@PathParam("id") Long id);

	@GET
	@Path("/list")
	public Response list(@QueryParam("startId") @DefaultValue("1") Long startId, @QueryParam("size") @DefaultValue("20") Integer size);
}
