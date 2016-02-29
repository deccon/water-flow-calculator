package com.aquatic.waterflow.resource;

import static com.aquatic.waterflow.WaterFlowConstants.*;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.validation.Valid;

import lombok.extern.log4j.Log4j;

import com.aquatic.waterflow.entity.RiverCrossSection;
import com.aquatic.waterflow.error.WaterFlowCalculatorException;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;

/**
 * WaterFlowResource to define available RESTful endpoints
 */
@Log4j
@Path(PATH_WATER_FLOW_ROOT)
public class WaterFlowResource {
	
	private WaterFlowService waterFlowService;
	
	@Inject
	public WaterFlowResource(WaterFlowService waterFlowService) {
		this.waterFlowService = waterFlowService;
	}

	@POST
	@Timed
	@Path(PATH_WATER_FLOW_POST_MEASUREMENTS)
	@Produces(MediaType.APPLICATION_JSON)
	public Response waterFlowMeasurements(@Valid RiverCrossSection crossSection) {
		try {
			log.info(LOG_NEW_CALCULATION_REQUEST);
			RiverCrossSection response = waterFlowService.processResults(crossSection);
			return Response.status(OK).entity(response).build();
		} catch (WaterFlowCalculatorException e) {
			log.error(LOG_ERROR_CALCULATION_REQUEST + e.getMessage());
			return Response.status(INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
