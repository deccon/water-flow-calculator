package com.aquatic.waterflow.entity;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonSnakeCase
@NoArgsConstructor
@AllArgsConstructor
public class RiverSubSection {
	private double width;
	private double depth;
	private double velocity;
}
