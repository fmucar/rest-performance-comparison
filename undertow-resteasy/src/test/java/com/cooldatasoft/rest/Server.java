package com.cooldatasoft.rest;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import com.cooldatasoft.rest.resource.SampleResource;

/**
 * Created by fmucar on 25/09/15.
 */
public class Server {

    public static void main(String[] args) throws ServletException {
        UndertowJaxrsServer server = new UndertowJaxrsServer().start();
        server.deploy(RestApplication.class);
    }

    @ApplicationPath("/sample")
    public static class MyApp extends Application {
        @Override
        public Set<Class<?>> getClasses() {
            HashSet<Class<?>> classes = new HashSet<Class<?>>();
            classes.add(SampleResource.class);
            return classes;
        }
    }

    //    @Test
    //    public void testApplicationPath() throws Exception
    //    {
    //        server.deploy(MyApp.class);
    //        Client client = ClientBuilder.newClient();
    //        String val = client.target(TestPortProvider.generateURL("/base/test"))
    //                .request().get(String.class);
    //        Assert.assertEquals("hello world", val);
    //        client.close();
    //    }

    //    @Test
    //    public void testApplicationContext() throws Exception
    //    {
    //        server.deploy(MyApp.class, "/root");
    //        Client client = ClientBuilder.newClient();
    //        String val = client.target(TestPortProvider.generateURL("/root/test"))
    //                .request().get(String.class);
    //        Assert.assertEquals("hello world", val);
    //        client.close();
    //    }

    //    @Test
    //    public void testDeploymentInfo() throws Exception
    //    {
    //        DeploymentInfo di = server.undertowDeployment(MyApp.class);
    //        di.setContextPath("/di");
    //        di.setDeploymentName("DI");
    //        server.deploy(di);
    //        Client client = ClientBuilder.newClient();
    //        String val = client.target(TestPortProvider.generateURL("/di/base/test"))
    //                .request().get(String.class);
    //        Assert.assertEquals("hello world", val);
    //        client.close();
    //    }
}
