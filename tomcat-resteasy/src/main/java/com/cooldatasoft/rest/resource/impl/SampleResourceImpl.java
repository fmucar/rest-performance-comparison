package com.cooldatasoft.rest.resource.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;

import com.cooldatasoft.rest.data.SampleEntity;
import com.cooldatasoft.rest.resource.SampleResource;
import com.cooldatasoft.rest.service.SampleService;

@Named
public class SampleResourceImpl implements SampleResource {

	@Inject
	private SampleService sampleService;

	@Override
	public Response create(SampleEntity entity) {
		return Response.ok(sampleService.create()).build();
	}

	@Override
	public Response read(Long id) {
		return Response.ok(sampleService.read(id)).build();
	}

	@Override
	public Response update(SampleEntity entity) {
		return Response.ok(sampleService.update(entity)).build();
	}

	@Override
	public Response delete(Long id) {
		return Response.ok(sampleService.delete(id)).build();
	}

	@Override
	public Response list(Long startId, Integer size) {
		return Response.ok(sampleService.list(startId, size)).build();
	}
}
