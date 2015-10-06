package com.cooldatasoft.rest;

import javax.servlet.ServletException;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ListenerInfo;

public class AppServer {

    public static void main(String[] args) {
        try {

            DeploymentInfo servletBuilder = Servlets.deployment()
                    .setClassLoader(AppServer.class.getClassLoader())
                    .setContextPath("/")
                    .setDeploymentName("test.war")
                    .addInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext")
                    .addInitParameter("contextConfigLocation", "com.cooldatasoft.rest.config.AppConfig")
                    .addListener(new ListenerInfo(ContextLoaderListener.class))

                    .addServlets(
                            Servlets.servlet("ExampleServlet", DispatcherServlet.class)
                                    .addInitParam("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext")
                                    .addInitParam("contextConfigLocation", "com.cooldatasoft.AppConfig")
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