package com.aquatic.waterflow.calculate;

import static com.aquatic.waterflow.WaterFlowConstants.LOG_SHOW_RIVER_FLOW_RESULTS;
import static com.aquatic.waterflow.WaterFlowConstants.LOG_CALCULATING_RIVER_FLOW_RESULTS;

import java.text.DecimalFormat;

import lombok.extern.log4j.Log4j;

import com.aquatic.waterflow.entity.RiverCrossSection;
import com.aquatic.waterflow.entity.RiverCrossSectionResults;
import com.aquatic.waterflow.entity.RiverSubSection;

/**
 * WaterFlowCalculator performs water flow calculation
 */
@Log4j
public class WaterFlowCalculator {

	/**
	 * Calculate the water flow for this {@link RiverCrossSection}
	 * 
	 * @param crossSection
	 * @return {@link RiverCrossSectionResults}
	 */
	public RiverCrossSectionResults calculateFlowResults(RiverCrossSection crossSection) {
		RiverCrossSectionResults results = new RiverCrossSectionResults();
		
		log.info(LOG_CALCULATING_RIVER_FLOW_RESULTS);

		double crossSectionArea = calculateTotalArea(crossSection);
		double crossSectionVelocity = calculateTotalVelocity(crossSection);	
		double crossSectionFlow = calculateTotalFlow(crossSection);
		
		results.setTotalArea(format(crossSectionArea));
		results.setTotalVelocty(format(crossSectionVelocity));
		results.setTotalFlow(format(crossSectionFlow));
		
		log.info(LOG_SHOW_RIVER_FLOW_RESULTS + results);

		return results;
	}
	
	/*
	 * Calculate the total area in the cross section
	 */
	private double calculateTotalArea(RiverCrossSection crossSection) {
		double totalArea = 0;
		
		for(RiverSubSection measurement : crossSection.getMeasurements()) {
			totalArea += calculateSubSectionArea(measurement);
		}
		
		return totalArea;
	}

	/*
	 * Calculate the total velocity in the cross section
	 */
	private double calculateTotalVelocity(RiverCrossSection crossSection) {
		double totalVelocity = 0;
		
		for(RiverSubSection measurement : crossSection.getMeasurements()) {
			totalVelocity += measurement.getVelocity();
		}
		
		return totalVelocity;
	}

	/*
	 * Calculate the total flow in the cross section
	 */
	private double calculateTotalFlow(RiverCrossSection crossSection) {
		double totalFlow = 0;
		
		for(RiverSubSection measurement : crossSection.getMeasurements()) {
			totalFlow += calculateSubSectionFlow(measurement);
		}
		
		return totalFlow;
	}

	/*
	 * Calculate flow for river sub section
	 */
	private double calculateSubSectionFlow(RiverSubSection measurement) {
		double subSectionArea = calculateSubSectionArea(measurement);
		return subSectionArea * measurement.getVelocity();
	}

	/*
	 * Calculate area for river sub section
	 */
	private double calculateSubSectionArea(RiverSubSection measurement) {
		return measurement.getDepth() * measurement.getWidth();
	}
	
	/*
	 * Format a double with two decimal places
	 */
	private double format(double value) {
		
		DecimalFormat df = new DecimalFormat("#.00"); 
		return Double.valueOf(df.format(value));
	}

}
