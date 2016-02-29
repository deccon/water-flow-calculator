package com.aquatic.waterflow.entity;

import io.dropwizard.jackson.JsonSnakeCase;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;

@Data
@JsonSnakeCase
public class RiverCrossSection {
	
	@Valid
	@JsonUnwrapped
	private River river;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm", timezone="PST")
	private Date dateEntered;
	
	@NotEmpty
	private List<RiverSubSection> measurements;
	
	private RiverCrossSectionResults results;
}
