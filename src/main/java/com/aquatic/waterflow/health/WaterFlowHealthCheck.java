package com.aquatic.waterflow.health;

import com.aquatic.waterflow.config.ApplicationConfiguration;
import com.codahale.metrics.health.HealthCheck;

/**
 * WaterFlowHealthCheck for system health checks
 */
public class WaterFlowHealthCheck extends HealthCheck {

	private ApplicationConfiguration configuration;

	public WaterFlowHealthCheck(ApplicationConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	protected Result check() throws Exception {
		if (configuration != null) {
			if (!configuration.getApplicationName().isEmpty()) {
				return Result.healthy();
			}
		}
		return Result.unhealthy("Error loading application configuration");
	}
}
