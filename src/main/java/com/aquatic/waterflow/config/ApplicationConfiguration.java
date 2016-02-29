package com.aquatic.waterflow.config;

import lombok.Getter;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

/**
 * ApplicationConfiguration holds values from config.yml
 */
@Getter
public class ApplicationConfiguration extends Configuration {
	
	@JsonProperty
	private String applicationName;
	
}
