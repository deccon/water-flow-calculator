package com.aquatic.waterflow;

import static com.aquatic.waterflow.WaterFlowConstants.HEALTH_CHECK_NAME_CONFIG;

import com.aquatic.waterflow.config.ApplicationConfiguration;
import com.aquatic.waterflow.health.WaterFlowHealthCheck;
import com.aquatic.waterflow.resource.WaterFlowResource;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Main class to start the {@link WaterFlowApplication}
 */
public class WaterFlowApplication extends Application<ApplicationConfiguration> {
	
    public static void main(String[] args) throws Exception {
    	new WaterFlowApplication().run(args);
    }

	@Override
	public void run(ApplicationConfiguration configuration,
			Environment environment) throws Exception {
		Injector injector = createInjector(configuration);
		environment.jersey().register(injector.getInstance(WaterFlowResource.class));
		
		environment.healthChecks().register(HEALTH_CHECK_NAME_CONFIG, new WaterFlowHealthCheck(configuration));
	}
	
	/*
	 * Configure injection for guice
	 */
	private Injector createInjector(final ApplicationConfiguration conf) {
	    return Guice.createInjector(new AbstractModule() {
	        @Override
	        protected void configure() {
	        	// configure injection instances
	        }
	    });
	}
}
