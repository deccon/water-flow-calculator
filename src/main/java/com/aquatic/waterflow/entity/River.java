package com.aquatic.waterflow.entity;

import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonSnakeCase
@NoArgsConstructor
@AllArgsConstructor
public class River {

	@NotEmpty
	private String name;
	
	@NotEmpty
	private String location;
}
