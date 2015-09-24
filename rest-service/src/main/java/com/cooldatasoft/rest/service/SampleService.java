package com.cooldatasoft.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.cooldatasoft.rest.data.SampleEntity;
import com.cooldatasoft.sampler.ObjectFactory;

@Named
public class SampleService {

	public Long create() {
		return ObjectFactory.sample(SampleEntity.class).getId();
	}

	public SampleEntity read(Long id) {
		SampleEntity entity = ObjectFactory.sample(SampleEntity.class);
		entity.setId(id);
		return entity;
	}

	public String update(SampleEntity entity) {
		return "updated entity with id: " + entity.getId();
	}

	public String delete(Long id) {
		return "deleted entity with id : " + id;
	}

	public List<SampleEntity> list(long startId, int size) {
		List<SampleEntity> result = new ArrayList<>();
		for(int i=0; i< size; i++) {
			SampleEntity sampleEntity = ObjectFactory.sample(SampleEntity.class);
			sampleEntity.setId(startId + i);
			result.add(sampleEntity);
		}
		return result;
	}
}
