package com.aquatic.waterflow.resource;

import static com.aquatic.waterflow.WaterFlowConstants.ERROR_RIVER_INPUT_INVALID;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.aquatic.waterflow.calculate.WaterFlowCalculator;
import com.aquatic.waterflow.entity.River;
import com.aquatic.waterflow.entity.RiverCrossSection;
import com.aquatic.waterflow.entity.RiverCrossSectionResults;
import com.aquatic.waterflow.entity.RiverSubSection;
import com.aquatic.waterflow.error.WaterFlowCalculatorException;

/**
 * Unit test for {@link WaterFlowService} class
 */
@RunWith(MockitoJUnitRunner.class)
public class WaterFlowServiceTest {
	
	@InjectMocks
	private WaterFlowService waterFlowService;

	@Mock
	private WaterFlowCalculator waterFlowCalculator;
	
	private RiverCrossSection crossSection;
	private List<RiverSubSection> measurements;

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		waterFlowService = new WaterFlowService(waterFlowCalculator);
		crossSection = createRiverCrossSection();
	}
	
	@Test
	public void testRiverCrossSectionRiver() throws WaterFlowCalculatorException {
		String riverName = "RiverABC";
		String riverLocation = "Vancouver, BC";
		crossSection.setRiver(new River(riverName, riverLocation));
		
		RiverCrossSection crossSectionResults = waterFlowService.processResults(crossSection);
		assertNotNull(crossSectionResults.getRiver());
		assertEquals(riverName, crossSectionResults.getRiver().getName());
		assertEquals(riverLocation, crossSectionResults.getRiver().getLocation());
	}
	
	@Test
	public void testRiverCrossSectionMeasurements() throws WaterFlowCalculatorException {
		
		Double width=1.0, depth=2.0, velocity=3.0;
		
		addSubSection(width, depth, velocity);
		
		RiverCrossSection crossSectionResults = waterFlowService.processResults(crossSection);
		assertNotNull(crossSectionResults.getMeasurements());
		
		RiverSubSection subSection = crossSectionResults.getMeasurements().get(0);
		assertTrue("Width in sub-section not correct", width.equals(subSection.getWidth()));
		assertTrue("Depth in sub-section not correct", depth.equals(subSection.getDepth()));
		assertTrue("Velocity in sub-section not correct", velocity.equals(subSection.getVelocity()));
	}
	
	@Test
	public void testRiverCrossSectionDate() throws WaterFlowCalculatorException {
		RiverCrossSection crossSectionResults = waterFlowService.processResults(crossSection);
		assertNotNull(crossSectionResults.getDateEntered());
	}
	
	@Test
	public void testRiverCrossSectionResults() throws WaterFlowCalculatorException {
		RiverCrossSectionResults expectedResults = new RiverCrossSectionResults(2, 4, 6);
		when(waterFlowCalculator.calculateFlowResults(crossSection)).thenReturn(expectedResults);
		
		RiverCrossSection crossSectionResults = waterFlowService.processResults(crossSection);
		assertEquals("Cross-section results not as expected", expectedResults, crossSectionResults.getResults());
	}

	@Test
	public void testRiverCrossSectionResultsNullInputData() throws WaterFlowCalculatorException {
		exception.expect(WaterFlowCalculatorException.class);
		exception.expectMessage(ERROR_RIVER_INPUT_INVALID);
		
		RiverCrossSection crossSection = null;
		waterFlowService.processResults(crossSection);
	}
	
	private RiverCrossSection createRiverCrossSection() {
		RiverCrossSection crossSection = new RiverCrossSection();
		measurements = new ArrayList<>();
		crossSection.setMeasurements(measurements);
		
		return crossSection;
	}
	
	private void addSubSection(double width, double depth, double velocity) {
		RiverSubSection subSection = new RiverSubSection(width, depth, velocity);
		measurements.add(subSection);
	}
}
