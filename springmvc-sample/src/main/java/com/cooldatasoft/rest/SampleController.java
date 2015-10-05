package com.cooldatasoft.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cooldatasoft.rest.data.SampleEntity;
import com.cooldatasoft.rest.service.SampleService;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Long create(@RequestBody SampleEntity entity) {
        return sampleService.create();
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public SampleEntity read(@PathVariable("id") Long id) {
        return sampleService.read(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@RequestBody SampleEntity entity) {
        return sampleService.update(entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
        return sampleService.delete(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<SampleEntity> list(@RequestParam(value = "startId", defaultValue = "1") Long startId, @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return sampleService.list(startId, size);
    }


}
