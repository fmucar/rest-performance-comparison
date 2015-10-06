package com.cooldatasoft.rest;

import javax.servlet.ServletException;

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderListener;
import org.jboss.resteasy.spi.ResteasyDeployment;

import com.sun.org.apache.regexp.internal.RE;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ListenerInfo;

public class AppServer {

    public static void main2(String[] args) {
        try {

            DeploymentInfo servletBuilder = Servlets.deployment()
                    .setClassLoader(AppServer.class.getClassLoader())
                    .setContextPath("/")
                    .setDeploymentName("test.war")
                    .addInitParameter("resteasy.servlet.mapping.prefix", "/v1")
                    .addInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext")
                    .addInitParameter("contextConfigLocation", "com.cooldatasoft.rest.config.AppConfig")
                    .addListener(new ListenerInfo(ResteasyBootstrap.class))
                    .addListener(new ListenerInfo(SpringContextLoaderListener.class))
                    .addServlets(
                            Servlets.servlet("ExampleServlet", HttpServlet30Dispatcher.class)
                                    .addMapping("/v1/*")
                    );

            DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
            manager.deploy();

            HttpHandler servletHandler = manager.start();
            PathHandler path = Handlers.path(Handlers.redirect("/"))
                    .addPrefixPath("/", servletHandler);
            Undertow server = Undertow.builder()
                    .addHttpListener(8080, "localhost")
                    .setHandler(path)
                    .build();
            server.start();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}