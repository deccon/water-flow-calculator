package com.aquatic.waterflow.resource;

import static com.aquatic.waterflow.WaterFlowConstants.ERROR_RIVER_INPUT_INVALID;

import java.util.Date;

import lombok.extern.log4j.Log4j;

import com.aquatic.waterflow.calculate.WaterFlowCalculator;
import com.aquatic.waterflow.entity.RiverCrossSection;
import com.aquatic.waterflow.entity.RiverCrossSectionResults;
import com.aquatic.waterflow.error.WaterFlowCalculatorException;
import com.google.inject.Inject;

/**
 * WaterFlowService to process calculation results
 */
@Log4j
public class WaterFlowService {
	
	private WaterFlowCalculator waterFlowCalculator;

	@Inject
	public WaterFlowService(WaterFlowCalculator waterFlowCalculator) {
		this.waterFlowCalculator = waterFlowCalculator;
	}

	/**
	 * Calculate total flow at this {@link RiverCrossSection}
	 * 
	 * @param {@link RiverCrossSection} measurements
	 * 
	 * @throws WaterFlowCalculatorException 
	 */
	public RiverCrossSection processResults(RiverCrossSection crossSection) throws WaterFlowCalculatorException {
		
		if(crossSection == null) {
			log.error(ERROR_RIVER_INPUT_INVALID);
			throw new WaterFlowCalculatorException(ERROR_RIVER_INPUT_INVALID);
		}
		
		RiverCrossSectionResults results = waterFlowCalculator.calculateFlowResults(crossSection);
		
		crossSection.setResults(results);
		crossSection.setDateEntered(new Date());
		
		return crossSection;
	}
}
