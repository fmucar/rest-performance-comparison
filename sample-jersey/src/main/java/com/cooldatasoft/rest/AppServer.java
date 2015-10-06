package com.cooldatasoft.rest; /**
 * Copyright (C) 2013 Guestful (info@guestful.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//import com.guestful.jersey.container.Container;

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

/**
 * date 2014-05-28
 *
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
public class AppServer {


    public static void main(String[] args) {
        try {

            DeploymentInfo servletBuilder = Servlets.deployment()
                    .setClassLoader(AppServer.class.getClassLoader())
                    .setContextPath("/")
                    .setDeploymentName("test.war")
                    .addInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext")
                    .addInitParameter("contextConfigLocation","com.cooldatasoft.rest.config.AppConfig")
                    .addListener(new ListenerInfo(ContextLoaderListener.class))

                    .addServlets(
                            Servlets.servlet("ExampleServlet", ServletContainer.class)
                                    .addInitParam("jersey.config.server.provider.packages", "com.cooldatasoft.rest.resource")
                                    .addMapping("/v1/*")
                                    .addInitParam("jersey.config.server.provider.classnames","org.codehaus.jackson.jaxrs.JacksonJsonProvider")
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
        }  catch (ServletException e) {
            e.printStackTrace();
        }
    }

}