package com.cooldatasoft.rest;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.util.Headers;

/**
 * Created by fmucar on 24/09/15.
 */
public class Server {

    public static void main(final String[] args) {
//        Undertow server = Undertow.builder()
//                .addHttpListener(8080, "localhost")
//                .setHandler(new HttpHandler() {
//                    @Override
//                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
//                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
//                        exchange.getResponseSender().send("Hello World");
//                    }
//                }).build();
//        server.start();


//        DeploymentInfo servletBuilder = Servlets.deployment()
//                .setClassLoader(Server.class.getClassLoader())
//                .setContextPath("/")
//                .setDeploymentName("test.war")
//                .addServlets(
//                        Servlets.servlet("MessageServlet", MessageServlet.class)
//                                .addInitParam("message", "Hello World")
//                                .addMapping("/*"),
//                        Servlets.servlet("MyServlet", MessageServlet.class)
//                                .addInitParam("message", "MyServlet")
//                                .addMapping("/myservlet"));
//
//        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
//        manager.deploy();
//        PathHandler path = Handlers.path(Handlers.redirect("/myapp"))
//                .addPrefixPath("/myapp", manager.start());
//
//        Undertow server = Undertow.builder()
//                .addHttpListener(8080, "localhost")
//                .setHandler(path)
//                .build();
//        server.start();
    }
}
