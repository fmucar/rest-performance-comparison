package com.cooldatasoft.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.cooldatasoft.rest.resource.SampleResource;

/**
 * Created by fmucar on 25/09/15.
 */
@ApplicationPath("/")
public class RestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(SampleResource.class);
        return classes;
    }
}
