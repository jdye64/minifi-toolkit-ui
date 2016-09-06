package org.apache.nifi.minifi.toolkit.ui;

import org.apache.nifi.minifi.toolkit.ui.resource.TransformResource;

import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by jeremydyer on 11/19/14.
 */
public class Application
        extends io.dropwizard.Application<ApplicationConfiguration> {

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
        //bootstrap.addCommand(new DummyCommand());

        //Creates an Asset bundle to serve up static content. Served from http://localhost:8080/assets/
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(ApplicationConfiguration configuration, Environment environment) throws Exception {

        //Non-static content requests should be accepted through "/api/*" URL formats
        environment.jersey().setUrlPattern("/api/*");

        //Register your Web Resources like below.
        final TransformResource transformResource = new TransformResource();
        environment.jersey().register(transformResource);
    }

    public static void main(String[] args) throws Exception {
        new Application().run(args);
    }
}