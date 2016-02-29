package com.aquatic.waterflow.resource;

import static com.aquatic.waterflow.WaterFlowConstants.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.dropwizard.testing.junit.ResourceTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.aquatic.waterflow.entity.River;
import com.aquatic.waterflow.entity.RiverCrossSection;
import com.aquatic.waterflow.entity.RiverSubSection;

/**
 * Unit test for {@link WaterFlowResource} class
 */
@RunWith(MockitoJUnitRunner.class)
public class WaterFlowResourceTest {
	
	private static final String BASE_URI = PATH_WATER_FLOW_ROOT;
	
	private RiverCrossSection crossSection;
	
	@Mock
	private static WaterFlowService waterFlowService;
	
	@InjectMocks
    private static WaterFlowResource waterFlowResource = new WaterFlowResource(waterFlowService);
	
	@Rule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
    		.addResource(waterFlowResource)
            .build();
	
	@Before
	public void setUp() throws Exception {
		crossSection = createRiverCrossSection();
	}

	@Test
	public void testPostWaterFlowMeasurements() {
        
		int expectedStatusCode = Status.OK.getStatusCode();
		Response response = resources.client().target(BASE_URI + PATH_WATER_FLOW_POST_MEASUREMENTS)
        		.request().post(Entity.json(crossSection));
		
        assertEquals("Incorrect status from endpoint", expectedStatusCode, response.getStatus());
	}
	
	@Test
	public void testPostWaterFlowMeasurementsBadData() {
        
		int expectedStatusCode = 422;
		crossSection = null;
		Response response = resources.client().target(BASE_URI + PATH_WATER_FLOW_POST_MEASUREMENTS)
        		.request().post(Entity.json(crossSection));
		
		assertEquals("Incorrect status from endpoint", expectedStatusCode, response.getStatus());
	}

	private RiverCrossSection createRiverCrossSection() {
		RiverCrossSection crossSection = new RiverCrossSection();
		List<RiverSubSection> measurements = new ArrayList<>();
		
		measurements.add(new RiverSubSection());
		
		River river = new River("name", "location");
		crossSection.setRiver(river);
		crossSection.setMeasurements(measurements);
		
		return crossSection;
	}
}
