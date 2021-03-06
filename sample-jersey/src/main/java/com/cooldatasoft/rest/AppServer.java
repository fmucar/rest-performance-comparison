package com.cooldatasoft.rest;

import javax.servlet.ServletException;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;

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
                            Servlets.servlet("ExampleServlet", ServletContainer.class)
                                    .addInitParam("jersey.config.server.provider.packages", "com.cooldatasoft.rest.resource")
                                    .addMapping("/v1/*")
                                    .addInitParam("jersey.config.server.provider.classnames", "org.codehaus.jackson.jaxrs.JacksonJsonProvider")
                                    .addInitParam("jersey.config.disableMoxyJson", "true")
                                    .setLoadOnStartup(1)
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