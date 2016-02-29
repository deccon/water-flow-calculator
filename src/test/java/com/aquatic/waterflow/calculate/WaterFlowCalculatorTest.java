package com.aquatic.waterflow.calculate;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.aquatic.waterflow.entity.RiverCrossSection;
import com.aquatic.waterflow.entity.RiverCrossSectionResults;
import com.aquatic.waterflow.entity.RiverSubSection;

/**
 * Unit test for {@link WaterFlowCalculator} class
 */
public class WaterFlowCalculatorTest {

	private WaterFlowCalculator waterFlowCalculator;
	private RiverCrossSection crossSection;
	private List<RiverSubSection> measurements;
	
	@Before
	public void setUp() throws Exception {
		waterFlowCalculator = new WaterFlowCalculator();
		crossSection = new RiverCrossSection();
		measurements = new ArrayList<>();
		
		crossSection.setMeasurements(measurements);
	}

	@Test
	public void testTotalFlow() {
		
		double width = 5;
		double depth = 4.45;
		double averageVelocity = 1.2;
		
		addSubSection(width, depth, averageVelocity);
		
		RiverCrossSectionResults results = waterFlowCalculator.calculateFlowResults(crossSection);
		
		Double expectedArea = (width * depth);
		Double expectedVelocity = averageVelocity;
		Double expectedFlow = expectedArea * averageVelocity;
		
		assertTrue("TotalArea not as expected", expectedArea.equals(results.getTotalArea()));
		assertTrue("TotalFlow not as expected", expectedVelocity.equals(results.getTotalVelocty()));
		assertTrue("TotalFlow not as expected", expectedFlow.equals(results.getTotalFlow()));
	}

	private void addSubSection(double width, double depth, double velocity) {
		RiverSubSection subSection = new RiverSubSection(width, depth, velocity);
		measurements.add(subSection);
	}

}
